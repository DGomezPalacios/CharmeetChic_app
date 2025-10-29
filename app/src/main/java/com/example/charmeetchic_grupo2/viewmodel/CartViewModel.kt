package com.example.charmeetchic_grupo2.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.charmeetchic_grupo2.model.CartItem
import com.example.charmeetchic_grupo2.model.Product

data class CartState(
    val items: List<CartItem> = emptyList(),
    val successMsg: String? = null
) {
    val total: Int get() = items.sumOf { it.subtotal }
}

class CartViewModel: ViewModel() {
    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state

    fun add(p: Product) {
        val current = _state.value.items.toMutableList()
        val idx = current.indexOfFirst { it.product.id == p.id }
        if (idx >= 0) current[idx] = current[idx].copy(qty = current[idx].qty + 1)
        else current += CartItem(p, 1)
        _state.value = _state.value.copy(items = current, successMsg = null)
    }

    fun remove(pId: String) {
        _state.value = _state.value.copy(items = _state.value.items.filter { it.product.id != pId })
    }

    fun dec(pId: String) {
        val list = _state.value.items.mapNotNull {
            if (it.product.id == pId) {
                val q = it.qty - 1
                if (q <= 0) null else it.copy(qty = q)
            } else it
        }
        _state.value = _state.value.copy(items = list)
    }

    fun clearAndSuccess() {
        _state.value = CartState(items = emptyList(), successMsg = "Compra realizada con éxito")
    }

    fun dismissMsg() { _state.value = _state.value.copy(successMsg = null) }
}
