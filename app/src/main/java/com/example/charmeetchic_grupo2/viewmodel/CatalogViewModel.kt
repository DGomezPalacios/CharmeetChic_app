package com.example.charmeetchic_grupo2.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.repository.ProductRepository

data class CatalogState(
    val query: String = "",
    val items: List<Product> = emptyList()
)

class CatalogViewModel: ViewModel() {
    private val _state = MutableStateFlow(CatalogState(items = ProductRepository.getAll()))
    val state: StateFlow<CatalogState> = _state

    fun onQueryChange(q: String) {
        _state.value = _state.value.copy(
            query = q,
            items = if (q.isBlank()) ProductRepository.getAll() else ProductRepository.searchByName(q)
        )
    }
}
