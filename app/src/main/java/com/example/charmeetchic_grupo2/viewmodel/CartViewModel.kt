package com.example.charmeetchic_grupo2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.CartItemDTO
import com.example.charmeetchic_grupo2.repository.CartRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repo: CartRepository = CartRepository(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _state = MutableStateFlow(CartState())
    val state: StateFlow<CartState> = _state

    private val usuarioId = 1L

    fun cargarCarrito() {
        viewModelScope.launch(dispatcher) {
            try {
                _state.value = _state.value.copy(cargando = true)

                val response = repo.obtenerCarrito(usuarioId)

                _state.value = CartState(
                    items = response.items,
                    total = response.total
                )

            } catch (e: Exception) {
                _state.value = CartState(mensaje = "Error al cargar carrito: ${e.message}")
            }
        }
    }

    fun add(product: com.example.charmeetchic_grupo2.model.Product) {
        agregar(product.id)
    }

    fun agregar(productoId: Long) {
        viewModelScope.launch(dispatcher) {
            try {
                repo.agregarAlCarrito(usuarioId, productoId)
                cargarCarrito()
            } catch (e: Exception) {
                _state.value = CartState(mensaje = "Error al agregar: ${e.message}")
            }
        }
    }

    fun actualizar(productoId: Long, cantidad: Int) {
        viewModelScope.launch(dispatcher) {
            try {
                repo.actualizarCarrito(usuarioId, productoId, cantidad)
                cargarCarrito()
            } catch (e: Exception) {
                _state.value = CartState(mensaje = "Error al actualizar: ${e.message}")
            }
        }
    }

    fun eliminar(productoId: Long) {
        viewModelScope.launch(dispatcher) {
            try {
                repo.eliminarDelCarrito(usuarioId, productoId)
                cargarCarrito()
            } catch (e: Exception) {
                _state.value = CartState(mensaje = "Error al eliminar: ${e.message}")
            }
        }
    }

    fun confirmarCompra() {
        viewModelScope.launch(dispatcher) {
            try {
                repo.confirmarCompra(usuarioId)
                _state.value = CartState(mensaje = "Compra realizada con éxito")
            } catch (e: Exception) {
                _state.value = CartState(mensaje = "Error al confirmar: ${e.message}")
            }
        }
    }

    fun limpiarMensaje() {
        _state.value = _state.value.copy(mensaje = null)
    }
}