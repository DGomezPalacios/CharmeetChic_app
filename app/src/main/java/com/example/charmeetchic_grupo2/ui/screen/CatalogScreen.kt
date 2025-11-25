package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.charmeetchic_grupo2.ui.components.ProductCard
import com.example.charmeetchic_grupo2.viewmodel.CatalogViewModel
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel
import kotlinx.coroutines.delay

@Composable
fun CatalogScreen(
    cartVM: CartViewModel,
    productVM: ProductViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val productos = productVM.productList
    val isLoading = productVM.isLoading
    val error = productVM.errorMessage

    var query by remember { mutableStateOf("") }

    // Se cargan los productos al entrar
    LaunchedEffect(Unit) {
        productVM.cargarProductos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🔍 Buscador
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                if (query.isBlank()) {
                    productVM.cargarProductos()
                } else {
                    productVM.buscar(query)
                }
            },
            label = { Text("Buscar producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // 🟡 Loading
        if (isLoading) {
            CircularProgressIndicator()
            return
        }

        // ❌ Error
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
            return
        }

        // 🟢 Lista de productos
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(productos, key = { it.id }) { p ->
                ProductCard(
                    product = p,
                    onAddToCart = { cartVM.add(p) }
                )
            }
        }
    }
}
