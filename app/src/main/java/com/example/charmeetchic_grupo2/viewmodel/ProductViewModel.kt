package com.example.charmeetchic_grupo2.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repo: ProductRepository = ProductRepository()
) : ViewModel() {

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
                productList = repo.listarTodos()
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }


    fun buscar(q: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                productList = repo.buscarPorNombre(q)
            } catch (e: Exception) {
                errorMessage = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}
