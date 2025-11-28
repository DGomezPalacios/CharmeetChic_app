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

    LaunchedEffect(Unit) {
        cartVM.cargarCarrito()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Carrito", style = MaterialTheme.typography.headlineSmall)

        if (state.items.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Tu carrito está vacío")
            }
            return@Column
        }

        LazyColumn(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.items, key = { it.id }) { ci ->

                Card {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column(Modifier.weight(1f)) {

                            Text(ci.producto.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Precio: \$${ci.producto.precio}")
                            Text("Cantidad: ${ci.cantidad}")
                            Text("Subtotal: \$${ci.producto.precio * ci.cantidad}")
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            OutlinedButton(onClick = {
                                if (ci.cantidad > 1)
                                    cartVM.actualizar(ci.productoId, ci.cantidad - 1)
                                else
                                    cartVM.eliminar(ci.productoId)
                            }) { Text("-") }

                            OutlinedButton(onClick = {
                                cartVM.actualizar(ci.productoId, ci.cantidad + 1)
                            }) { Text("+") }

                            TextButton(
                                onClick = { cartVM.eliminar(ci.productoId) }
                            ) { Text("Quitar") }
                        }
                    }
                }
            }
        }

        Text("Total: \$${state.total}", style = MaterialTheme.typography.titleMedium)

        Button(
            onClick = { cartVM.confirmarCompra() },
            modifier = Modifier.fillMaxWidth(),
            enabled = state.items.isNotEmpty()
        ) {
            Text("Finalizar compra")
        }

        AnimatedVisibility(
            visible = state.mensaje != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(state.mensaje ?: "", modifier = Modifier.padding(12.dp))
            }
        }

        if (state.mensaje != null) {
            LaunchedEffect(state.mensaje) {
                delay(2000)
                cartVM.limpiarMensaje()
            }
        }
    }
}
