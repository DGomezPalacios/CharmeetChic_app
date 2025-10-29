package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charmeetchic_grupo2.viewmodel.ContactViewModel

@Composable
fun ContactScreen(vm: ContactViewModel = viewModel()) {
    val s by vm.state.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Contacto", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = s.nombre,
            onValueChange = vm::onNombreChange,
            label = { Text("Nombre") },
            isError = s.errNombre != null,
            supportingText = { s.errNombre?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = s.correo,
            onValueChange = vm::onCorreoChange,
            label = { Text("Correo") },
            isError = s.errCorreo != null,
            supportingText = { s.errCorreo?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = s.mensaje,
            onValueChange = vm::onMensajeChange,
            label = { Text("Mensaje") },
            isError = s.errMensaje != null,
            supportingText = { s.errMensaje?.let { Text(it, color = MaterialTheme.colorScheme.error) } },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )
        Button(onClick = vm::submit, modifier = Modifier.fillMaxWidth()) {
            Text("Enviar")
        }

        if (s.enviado) {
            AssistChip(onClick = vm::reset, label = { Text("¡Mensaje enviado! Toca para limpiar.") })
        }
    }
}
