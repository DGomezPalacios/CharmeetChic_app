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
    catalogVM: CatalogViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    cartVM: CartViewModel
) {
    val state by catalogVM.state.collectAsState()
    val cartState by cartVM.state.collectAsState() // acceder al mensaje

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🔹 Buscador
        OutlinedTextField(
            value = state.query,
            onValueChange = { catalogVM.onQueryChange(it) },
            label = { Text("Buscar producto") },
            modifier = Modifier.fillMaxWidth()
        )

        // 🔹 Mensaje visible y legible justo debajo
        cartState.successMsg?.let { msg ->
            Spacer(Modifier.height(10.dp))
            Text(
                text = msg,
                color = Color(0xFF333333), // gris oscuro legible
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
                )
            )

            // Se borra solo después de 2 segundos
            LaunchedEffect(msg) {
                delay(2000)
                cartVM.dismissMsg()
            }
        }

        Spacer(Modifier.height(12.dp))

        // 🔹 Lista de productos
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(state.items, key = { it.id }) { p ->
                ProductCard(
                    product = p,
                    onAddToCart = {
                        cartVM.add(p)
                    }
                )
            }
        }
    }
}
