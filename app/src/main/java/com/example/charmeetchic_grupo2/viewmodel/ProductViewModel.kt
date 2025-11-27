package com.example.charmeetchic_grupo2.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.model.ProductRequest
import com.example.charmeetchic_grupo2.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    var productList by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set


    fun cargarProductos() {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                productList = repository.getAllProducts()
            } catch (e: Exception) {
                errorMessage = "Error al cargar productos: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun crearProducto(req: ProductRequest) {
        viewModelScope.launch {
            try {
                repository.createProduct(req)
                cargarProductos()
            } catch (e: Exception) {
                errorMessage = "Error al crear producto"
            }
        }
    }

    fun actualizarProducto(id: Long, req: ProductRequest) {
        viewModelScope.launch {
            try {
                repository.updateProduct(id, req)
                cargarProductos()
            } catch (e: Exception) {
                errorMessage = "Error al actualizar"
            }
        }
    }

    fun eliminarProducto(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteProduct(id)
                cargarProductos()
            } catch (e: Exception) {
                errorMessage = "No se pudo eliminar"
            }
        }
    }
}
