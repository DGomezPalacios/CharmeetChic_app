package com.example.charmeetchic_grupo2.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.model.ProductRequest
import com.example.charmeetchic_grupo2.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository = ProductRepository(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var productList = mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading = mutableStateOf(false)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun cargarProductos() {
        viewModelScope.launch(dispatcher) {
            try {
                isLoading.value = true
                errorMessage.value = null
                productList.value = repository.getAllProducts()
            } catch (e: Exception) {
                errorMessage.value = "Error al cargar productos: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    fun crearProducto(req: ProductRequest) {
        viewModelScope.launch(dispatcher) {
            try {
                repository.createProduct(req)
                cargarProductos()
            } catch (e: Exception) {
                errorMessage.value = "Error al crear producto"
            }
        }
    }

    fun actualizarProducto(id: Long, req: ProductRequest) {
        viewModelScope.launch(dispatcher) {
            try {
                repository.updateProduct(id, req)
                cargarProductos()
            } catch (e: Exception) {
                errorMessage.value = "Error al actualizar"
            }
        }
    }

    fun eliminarProducto(id: Long) {
        viewModelScope.launch(dispatcher) {
            try {
                repository.deleteProduct(id)
                cargarProductos()
            } catch (e: Exception) {
                errorMessage.value = "No se pudo eliminar"
            }
        }
    }
}
