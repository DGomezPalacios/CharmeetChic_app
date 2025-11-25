package com.example.charmeetchic_grupo2.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.repository.ProductoRepository
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    // Estado de la UI
    var productList by mutableStateOf<List<Product>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Obtener todos los productos
    fun cargarProductos() {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val productos = repository.listarTodos()

                productList = productos
            } catch (e: Exception) {
                errorMessage = "Error al cargar productos: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    // Buscar por nombre
    fun buscar(nombre: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val productos = repository.buscarPorNombre(nombre)

                productList = productos
            } catch (e: Exception) {
                errorMessage = "Error al buscar productos: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
