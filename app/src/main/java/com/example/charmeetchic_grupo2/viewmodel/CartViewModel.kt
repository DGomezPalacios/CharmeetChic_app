package com.example.charmeetchic_grupo2.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.Cart
import com.example.charmeetchic_grupo2.model.CartItem
import com.example.charmeetchic_grupo2.model.Compras
import com.example.charmeetchic_grupo2.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CartRepository = CartRepository()
) : ViewModel() {

    var cart by mutableStateOf(Cart())
        private set

    var successMsg by mutableStateOf<String?>(null)
        private set

    fun add(product: com.example.charmeetchic_grupo2.model.Product) {
        val updated = cart.items.toMutableList()

        val idx = updated.indexOfFirst { it.product.id == product.id }
        if (idx == -1) {
            updated.add(CartItem(product, 1))
        } else {
            updated[idx] = updated[idx].copy(qty = updated[idx].qty + 1)
        }

        cart = cart.copy(items = updated)
    }

    fun dismissMsg() {
        successMsg = null
    }

    // convertidor Cart en Compras para buen uso del backend
    private fun cartToCompras(): Compras {
        return Compras(
            id = null,
            usuarioId = null,      // lo conectamos cuando agreguen login real
            estado = "en carrito",
            total = cart.total.toDouble()
        )
    }

    // guardar en el backend
    fun guardarCarritoEnBackend() {
        viewModelScope.launch {
            try {
                val compra = cartToCompras()  // ← conversión aquí
                repository.agregarAlCarrito(compra)

                successMsg = "Carrito guardado correctamente"
            } catch (e: Exception) {
                successMsg = "Error al guardar: ${e.message}"
            }
        }
    }
}
