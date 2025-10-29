package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.charmeetchic_grupo2.R

@Composable
fun AboutUsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally // ✅ Centra el contenido
    ) {
        Text(
            text = "Quiénes Somos",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "En Charme et Chic creemos que cada joya guarda una historia. " +
                    "Nacimos como un emprendimiento de joyería artesanal con la misión de " +
                    "combinar elegancia, creatividad y dedicación en cada pieza.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )

        Text(
            text = "Nos especializamos en creación, personalización y reparación de joyas, " +
                    "buscando que cada diseño sea único y refleje la identidad de quien lo lleva.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )

        Text(
            text = "Nuestro equipo está comprometido con ofrecer un servicio cercano y de calidad, " +
                    "acompañando a nuestros clientes en cada momento especial con piezas que perduran.",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.charme_about),
            contentDescription = "Taller Charme et Chic",
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(top = 8.dp),
            contentScale = ContentScale.Crop
        )
    }
}
