package com.example.charmeetchic_grupo2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.ui.theme.Dorado
import com.example.charmeetchic_grupo2.ui.theme.TextoOscuro

@Composable
fun ProductCard(
    product: Product,
    onAddToCart: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // 🪞 Nombre del producto
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurface
            )

            // 💰 Precio más visible y elegante
            Text(
                text = "$${product.price}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextoOscuro.copy(alpha = 0.9f) // 🔹 Negro suave, más notorio
                // Si prefieres un toque dorado elegante: color = Dorado.copy(alpha = 0.9f)
            )

            // 🛒 Botón agregar al carrito
            Button(
                onClick = onAddToCart,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text("Agregar al carrito")
            }
        }
    }
}
