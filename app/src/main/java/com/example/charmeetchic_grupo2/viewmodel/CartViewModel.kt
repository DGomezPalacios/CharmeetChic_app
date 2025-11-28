package com.example.charmeetchic_grupo2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.CartItemDTO
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class CartState(
    val items: List<CartItemDTO> = emptyList(),
    val total: Double = 0.0,
    val cargando: Boolean = false,
    val mensaje: String? = null
)

class CartViewModel(
    private val repo: CartRepository = CartRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state

    private val usuarioId = 1L

    fun cargarCarrito() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(cargando = true)

                val response = repo.obtenerCarrito(usuarioId)

                _state.value = CartState(
                    items = response.items,
                    total = response.total
                )

            } catch (e: Exception) {
                _state.value = CartState(
                    mensaje = "Error al cargar carrito: ${e.message}"
                )
            }
        }
    }

    fun add(product: Product) {
        agregar(product.id)
    }

    fun agregar(productoId: Long) {
        viewModelScope.launch {
            try {
                repo.agregarAlCarrito(usuarioId, productoId)
                cargarCarrito()
            } catch (e: Exception) {
                _state.value = _state.value.copy(mensaje = "Error al agregar: ${e.message}")
            }
        }
    }

    fun actualizar(productoId: Long, cantidad: Int) {
        viewModelScope.launch {
            try {
                repo.actualizarCarrito(usuarioId, productoId, cantidad)
                cargarCarrito()
            } catch (e: Exception) {
                _state.value = _state.value.copy(mensaje = "Error al actualizar: ${e.message}")
            }
        }
    }

    fun eliminar(productoId: Long) {
        viewModelScope.launch {
            try {
                repo.eliminarDelCarrito(usuarioId, productoId)
                cargarCarrito()
            } catch (e: Exception) {
                _state.value = _state.value.copy(mensaje = "Error al eliminar: ${e.message}")
            }
        }
    }

    fun vaciar() {
        viewModelScope.launch {
            try {
                repo.vaciarCarrito(usuarioId)
                cargarCarrito()
            } catch (e: Exception) {
                _state.value = _state.value.copy(mensaje = "Error al vaciar: ${e.message}")
            }
        }
    }

    fun confirmarCompra() {
        viewModelScope.launch {
            try {
                repo.confirmarCompra(usuarioId)
                _state.value = CartState(
                    mensaje = "Compra realizada con éxito"
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(mensaje = "Error al confirmar: ${e.message}")
            }
        }
    }

    fun limpiarMensaje() {
        _state.value = _state.value.copy(mensaje = null)
    }
}
