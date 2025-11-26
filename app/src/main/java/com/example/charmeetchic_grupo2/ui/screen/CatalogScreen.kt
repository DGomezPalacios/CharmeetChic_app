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
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel
import com.example.charmeetchic_grupo2.viewmodel.ProductViewModel

@Composable
fun CatalogScreen(
    cartVM: CartViewModel,
    productVM: ProductViewModel = viewModel()
) {
    val productos = productVM.productList
    val isLoading = productVM.isLoading
    val error = productVM.errorMessage

    var query by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        productVM.cargarProductos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                if (query.isBlank()) productVM.cargarProductos()
                else productVM.buscar(query)
            },
            label = { Text("Buscar producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        if (isLoading) {
            CircularProgressIndicator()
            return
        }

        if (error != null) {
            Text(error, color = MaterialTheme.colorScheme.error)
            return
        }

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
