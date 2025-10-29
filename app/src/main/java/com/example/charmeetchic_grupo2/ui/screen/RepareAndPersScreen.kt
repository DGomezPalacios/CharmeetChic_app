package com.example.charmeetchic_grupo2.ui.screen

// imports clave
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
import androidx.compose.ui.Modifier           // <— explícito para evitar el error
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

//saveable no nulo
private val UriSaver: Saver<Uri?, String> = Saver(
    save = { it?.toString() ?: "" },
    restore = { s -> if (s.isEmpty()) null else Uri.parse(s) }
)

@Composable
fun RepareAndPersScreen() {
    var selected by rememberSaveable(stateSaver = UriSaver) { mutableStateOf<Uri?>(null) }
    var note by remember { mutableStateOf("") }

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri -> selected = uri }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Reparación y Personalización", style = MaterialTheme.typography.headlineSmall)

        Button(
            onClick = {
                picker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Adjuntar foto de la joya") }

        AnimatedVisibility(visible = selected != null) {
            Card(Modifier.fillMaxWidth().animateContentSize()) {
                Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    AsyncImage(
                        model = selected,
                        contentDescription = "Foto seleccionada",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                    OutlinedTextField(
                        value = note,
                        onValueChange = { note = it },
                        label = { Text("Comentario (opcional)") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Button(
            onClick = { /* TODO: enviar pedido con URI y nota */ },
            enabled = selected != null,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Enviar solicitud") }
    }
}
