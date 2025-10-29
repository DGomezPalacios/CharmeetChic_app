package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AboutUsScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Sobre Charme et Chic", style = MaterialTheme.typography.headlineSmall)
        Text("Joyería artesanal, personalización y reparación con cariño y detalle.")
        Text("Misión: acercar piezas únicas a cada persona. Visión: calidad, cercanía y sostenibilidad.")
    }
}
