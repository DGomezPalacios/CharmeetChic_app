package com.example.charmeetchic_grupo2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.data.local.CartStore
import com.example.charmeetchic_grupo2.model.CartItem
import com.example.charmeetchic_grupo2.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class CartState(
    val items: List<CartItem> = emptyList(),
    val successMsg: String? = null,
    val savedInfo: String? = null
) {
    val total: Int get() = items.sumOf { it.subtotal }
}

class CartViewModel(application: Application) : AndroidViewModel(application) {

    private val cartStore = CartStore(application.applicationContext)

    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state

    init {
        // Al iniciar, carga el resumen guardado en DataStore (si existe)
        viewModelScope.launch {
            val info = cartStore.cartInfo.first()
            _state.value = _state.value.copy(savedInfo = info)
        }
    }

    fun add(p: Product) {
        val current = _state.value.items.toMutableList()
        val idx = current.indexOfFirst { it.product.id == p.id }
        if (idx >= 0) current[idx] = current[idx].copy(qty = current[idx].qty + 1)
        else current += CartItem(p, 1)

        _state.value = _state.value.copy(
            items = current,
            successMsg = "${p.name} agregado al carrito ✅"
        )

        // Opcional: guardar persistencia simple
        viewModelScope.launch {
            saveCartSummary(current)
        }
    }

    fun remove(pId: String) {
        val updated = _state.value.items.filter { it.product.id != pId }
        updateCart(updated)
    }

    fun dec(pId: String) {
        val list = _state.value.items.mapNotNull {
            if (it.product.id == pId) {
                val q = it.qty - 1
                if (q <= 0) null else it.copy(qty = q)
            } else it
        }
        updateCart(list)
    }

    fun clearAndSuccess() {
        _state.value = CartState(items = emptyList(), successMsg = "Compra realizada con éxito")
        viewModelScope.launch { cartStore.clearCart() }
    }

    fun dismissMsg() {
        _state.value = _state.value.copy(successMsg = null)
    }

    // Guarda un resumen simple en DataStore (cantidad + total)
    private fun saveCartSummary(items: List<CartItem>) {
        viewModelScope.launch {
            val totalItems = items.sumOf { it.qty }
            val totalPrice = items.sumOf { it.subtotal }
            val summary = "Productos: $totalItems | Total: $totalPrice CLP"
            cartStore.saveCartInfo(summary)
        }
    }

    // Actualiza el estado del carrito y guarda el resumen en DataStore
    private fun updateCart(newList: List<CartItem>) {
        _state.value = _state.value.copy(items = newList, successMsg = null)
        saveCartSummary(newList)
    }

}
