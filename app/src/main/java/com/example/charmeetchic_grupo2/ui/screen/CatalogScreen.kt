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
    val productos = productVM.productList.value
    val isLoading = productVM.isLoading.value
    val error = productVM.errorMessage.value

    LaunchedEffect(Unit) {
        productVM.cargarProductos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        var query by remember { mutableStateOf("") }

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar productos…") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
            return@Column
        }

        if (error != null) {
            Text(error, color = MaterialTheme.colorScheme.error)
            return@Column
        }

        LazyColumn {
            items(productos) { producto ->
                ProductCard(
                    product = producto,
                    onAddToCart = { cartVM.add(producto) }
                )
                Spacer(Modifier.height(12.dp))
            }
        }
    }
}