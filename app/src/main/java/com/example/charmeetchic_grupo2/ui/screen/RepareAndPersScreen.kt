package com.example.charmeetchic_grupo2.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

// Guardar imagen seleccionada
private val UriSaver: Saver<Uri?, String> = Saver(
    save = { it?.toString() ?: "" },
    restore = { s -> if (s.isEmpty()) null else Uri.parse(s) }
)

@Composable
fun RepareAndPersScreen(
    onGoBack: () -> Unit,
    onSendRequest: () -> Unit
) {
    var selected by rememberSaveable(stateSaver = UriSaver) { mutableStateOf<Uri?>(null) }
    var serviceType by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var showSuccess by remember { mutableStateOf(false) }

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri -> selected = uri }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Título principal
        Text(
            text = "💍 Reparación y Personalización",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            "Completa tus datos, cuéntanos lo que deseas hacer y adjunta una imagen de tu joya.",
            style = MaterialTheme.typography.bodyMedium
        )

        // Datos del cliente
        Text("Datos del cliente", fontWeight = FontWeight.SemiBold)
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre completo") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Tipo de servicio
        Text("Tipo de servicio", fontWeight = FontWeight.SemiBold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            FilterChip(
                selected = serviceType == "Reparación",
                onClick = { serviceType = "Reparación" },
                label = { Text("Reparación") }
            )
            FilterChip(
                selected = serviceType == "Personalización",
                onClick = { serviceType = "Personalización" },
                label = { Text("Personalización") }
            )
        }

        // Descripción del trabajo
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción del trabajo") },
            placeholder = { Text("Ej: soldar cadena, ajustar tamaño, grabar iniciales...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = false,
            maxLines = 3
        )

        // Imagen de la joya al final del formulario
        Button(
            onClick = {
                picker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📸 Adjuntar imagen")
        }

        AnimatedVisibility(visible = selected != null) {
            Card(Modifier.fillMaxWidth().animateContentSize()) {
                AsyncImage(
                    model = selected,
                    contentDescription = "Foto seleccionada",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }

        // Confirmación de envío
        AnimatedVisibility(visible = showSuccess) {
            Text(
                "✅ Solicitud enviada con éxito. ¡Nos pondremos en contacto contigo pronto!",
                color = MaterialTheme.colorScheme.primary
            )
        }

        // Botones inferiores
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                onClick = onGoBack,
                modifier = Modifier.weight(1f)
            ) { Text("Volver") }

            Button(
                onClick = {
                    if (
                        serviceType.isNotEmpty() &&
                        description.isNotEmpty() &&
                        selected != null &&
                        name.isNotEmpty() &&
                        phone.isNotEmpty() &&
                        email.isNotEmpty()
                    ) {
                        showSuccess = true
                        onSendRequest()
                    }
                },
                enabled = serviceType.isNotEmpty() && description.isNotEmpty() && selected != null,
                modifier = Modifier.weight(1f)
            ) { Text("Enviar") }
        }
    }
}
