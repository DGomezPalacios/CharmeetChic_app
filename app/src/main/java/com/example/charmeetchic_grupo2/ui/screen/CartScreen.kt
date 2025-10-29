package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel

@Composable
fun CartScreen(cartVM: CartViewModel = viewModel()) {
    val state by cartVM.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Carrito", style = MaterialTheme.typography.headlineSmall)

        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.weight(1f)) {
            items(state.items, key = { it.product.id }) { ci ->
                Card {
                    Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
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

        Text("Total: \$${state.total}", style = MaterialTheme.typography.titleMedium)
        Button(
            onClick = { cartVM.clearAndSuccess() },
            enabled = state.items.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) { Text("Finalizar compra") }

        state.successMsg?.let {
            Snackbar(
                action = {
                    TextButton(onClick = { cartVM.dismissMsg() }) { Text("OK") }
                }
            ) { Text(it) }
        }
    }
}
