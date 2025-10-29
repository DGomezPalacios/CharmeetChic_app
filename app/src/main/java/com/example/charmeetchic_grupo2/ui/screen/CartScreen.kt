package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel
import kotlinx.coroutines.delay

@Composable
fun CartScreen(
    cartVM: CartViewModel   // ✅ se recibe el compartido desde el NavGraph
) {
    val state by cartVM.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Carrito", style = MaterialTheme.typography.headlineSmall)

        if (state.items.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("Tu carrito está vacío")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(state.items, key = { it.product.id }) { ci ->
                    Card {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(ci.product.name, style = MaterialTheme.typography.titleMedium)
                                Text("Precio: \$${ci.product.price}  ·  Cant: ${ci.qty}")
                                Text("Subtotal: \$${ci.subtotal}")
                            }
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                OutlinedButton(onClick = { cartVM.dec(ci.product.id) }) { Text("-") }
                                OutlinedButton(onClick = { cartVM.add(ci.product) }) { Text("+") }
                                TextButton(onClick = { cartVM.remove(ci.product.id) }) { Text("Quitar") }
                            }
                        }
                    }
                }
            }
        }

        Text(
            "Total: \$${state.total}",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium)
        )

        Button(
            onClick = { cartVM.clearAndSuccess() },
            enabled = state.items.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Finalizar compra") }

        // ✅ Mensaje de éxito con animación (simple y claro)
        AnimatedVisibility(
            visible = state.successMsg != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = state.successMsg ?: "",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        // Se oculta automáticamente después de 2s
        LaunchedEffect(state.successMsg) {
            if (state.successMsg != null) {
                delay(2000)
                cartVM.dismissMsg()
            }
        }
    }
}
