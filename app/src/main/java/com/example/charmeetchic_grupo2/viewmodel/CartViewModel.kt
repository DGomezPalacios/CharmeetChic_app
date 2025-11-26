package com.example.charmeetchic_grupo2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.Cart
import com.example.charmeetchic_grupo2.model.CartItem
import com.example.charmeetchic_grupo2.model.Compras
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// ⭐ ESTA ES LA ÚNICA DEFINICIÓN VÁLIDA
data class CartState(
    val items: List<CartItem> = emptyList(),
    val total: Double = 0.0,
    val successMsg: String? = null
)

class CartViewModel(
    private val repository: CartRepository = CartRepository()
) : ViewModel() {

    // Estado interno
    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state

    // ➕ Agregar producto
    fun add(product: Product) {
        _state.value = _state.value.let { current ->
            val updated = current.items.toMutableList()

            val idx = updated.indexOfFirst { it.product.id == product.id }
            if (idx == -1) {
                updated.add(CartItem(product, 1))
            } else {
                updated[idx] = updated[idx].copy(qty = updated[idx].qty + 1)
            }

            CartState(
                items = updated,
                total = updated.sumOf { it.subtotal },
                successMsg = null
            )
        }
    }

    // ➖ Disminuir cantidad o quitar
    fun dec(productId: Long) {
        _state.value = _state.value.let { current ->
            val updated = current.items.toMutableList()
            val idx = updated.indexOfFirst { it.product.id == productId }

            if (idx != -1) {
                val item = updated[idx]
                if (item.qty > 1) {
                    updated[idx] = item.copy(qty = item.qty - 1)
                } else {
                    updated.removeAt(idx)
                }
            }

            CartState(
                items = updated,
                total = updated.sumOf { it.subtotal },
                successMsg = null
            )
        }
    }

    // ❌ Eliminar un producto
    fun remove(productId: Long) {
        _state.value = _state.value.let { current ->
            val updated = current.items.filterNot { it.product.id == productId }

            CartState(
                items = updated,
                total = updated.sumOf { it.subtotal },
                successMsg = null
            )
        }
    }

    // 🗑 Limpiar y mostrar mensaje de éxito
    fun clearAndSuccess() {
        _state.value = CartState(
            items = emptyList(),
            total = 0.0,
            successMsg = "Compra realizada con éxito"
        )
    }

    fun dismissMsg() {
        _state.value = _state.value.copy(successMsg = null)
    }

    // ⭐ Convertir carrito → Compras para backend
    private fun cartToCompras(): Compras {
        return Compras(
            id = null,
            usuarioId = null,
            estado = "en carrito",
            total = _state.value.total
        )
    }

    // ⭐ Guardar en backend
    fun guardarCarritoEnBackend() {
        viewModelScope.launch {
            try {
                val compra = cartToCompras()
                repository.agregarAlCarrito(compra)

                _state.value = _state.value.copy(successMsg = "Carrito guardado correctamente")

            } catch (e: Exception) {
                _state.value = _state.value.copy(successMsg = "Error al guardar: ${e.message}")
            }
        }
    }
}
