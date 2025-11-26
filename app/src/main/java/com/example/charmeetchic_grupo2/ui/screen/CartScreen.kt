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
import androidx.compose.ui.unit.dp
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel
import kotlinx.coroutines.delay

@Composable
fun CartScreen(cartVM: CartViewModel) {
    val state by cartVM.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Carrito", style = MaterialTheme.typography.headlineSmall)

        if (state.items.isEmpty()) {
            Box(
                Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("Tu carrito está vacío")
            }
        } else {
            LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(state.items, key = { it.product.id }) { ci ->
                    Card {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(Modifier.weight(1f)) {
                                Text(ci.product.nombre, style = MaterialTheme.typography.titleMedium)
                                Text("Precio: \$${ci.product.precio}")
                                Text("Cantidad: ${ci.qty}")
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
            style = MaterialTheme.typography.titleMedium
        )

        Button(
            onClick = { cartVM.clearAndSuccess() },
            enabled = state.items.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Finalizar compra")
        }

        AnimatedVisibility(
            visible = state.successMsg != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = state.successMsg ?: "",
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        if (state.successMsg != null) {
            LaunchedEffect(state.successMsg) {
                delay(2000)
                cartVM.dismissMsg()
            }
        }
    }
}
