package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charmeetchic_grupo2.ui.components.ProductCard
import com.example.charmeetchic_grupo2.viewmodel.CatalogViewModel
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel

@Composable
fun CatalogScreen(
    catalogVM: CatalogViewModel = viewModel(),
    cartVM: CartViewModel = viewModel()
) {
    val state by catalogVM.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = state.query,
            onValueChange = { catalogVM.onQueryChange(it) },
            label = { Text("Buscar producto") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(state.items, key = { it.id }) { p ->
                ProductCard(product = p, onAddToCart = { cartVM.add(p) })
            }
        }
    }
}
