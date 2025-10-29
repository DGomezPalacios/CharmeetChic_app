package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight

@Composable
fun RepareAndPersScreen(
    onGoBack: () -> Unit,        // volver al Home o al menú principal
    onSendRequest: () -> Unit    // acción al enviar formulario
) {
    val bg = MaterialTheme.colorScheme.surfaceVariant

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isValid by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Reparación y Personalización",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Completa el formulario para solicitar una reparación o personalización de tu joya.",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre completo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Describe el servicio que necesitas") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )

            if (!isValid) {
                Text(
                    text = "Por favor, completa todos los campos antes de enviar.",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(onClick = onGoBack) {
                    Text("Volver")
                }

                Button(onClick = {
                    if (name.isNotBlank() && email.isNotBlank() && description.isNotBlank()) {
                        onSendRequest()
                    } else {
                        isValid = false
                    }
                }) {
                    Text("Enviar solicitud")
                }
            }
        }
    }
}
