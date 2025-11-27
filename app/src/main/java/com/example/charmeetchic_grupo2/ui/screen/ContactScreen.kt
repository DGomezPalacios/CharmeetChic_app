package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charmeetchic_grupo2.viewmodel.ContactViewModel

@Composable
fun ContactScreen(vm: ContactViewModel = viewModel()) {

    val state = vm.state
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(scroll),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Contacto",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        /* -------------------------
         *   Nombre
         * ------------------------- */
        OutlinedTextField(
            value = state.name,
            onValueChange = vm::onNameChange,
            label = { Text("Nombre") },
            isError = state.errName != null,
            modifier = Modifier.fillMaxWidth()
        )
        state.errName?.let {
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        /* -------------------------
         *   Email
         * ------------------------- */
        OutlinedTextField(
            value = state.email,
            onValueChange = vm::onEmailChange,
            label = { Text("Correo electrónico") },
            isError = state.errEmail != null,
            modifier = Modifier.fillMaxWidth()
        )
        state.errEmail?.let {
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        /* -------------------------
         *   Teléfono
         * ------------------------- */
        OutlinedTextField(
            value = state.phone,
            onValueChange = vm::onPhoneChange,
            label = { Text("Teléfono") },
            isError = state.errPhone != null,
            modifier = Modifier.fillMaxWidth()
        )
        state.errPhone?.let {
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        /* -------------------------
         *   Tipo de servicio
         * ------------------------- */
        OutlinedTextField(
            value = state.serviceType,
            onValueChange = vm::onServiceTypeChange,
            label = { Text("Tipo de servicio (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        /* -------------------------
         *   Imagen URL
         * ------------------------- */
        OutlinedTextField(
            value = state.imageUrl,
            onValueChange = vm::onImageUrlChange,
            label = { Text("Imagen URL (opcional)") },
            isError = state.errImageUrl != null,
            modifier = Modifier.fillMaxWidth()
        )
        state.errImageUrl?.let {
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        /* -------------------------
         *   Mensaje
         * ------------------------- */
        OutlinedTextField(
            value = state.message,
            onValueChange = vm::onMessageChange,
            label = { Text("Mensaje") },
            isError = state.errMessage != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        )
        state.errMessage?.let {
            Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
        }

        /* -------------------------
         *   Botón Enviar
         * ------------------------- */
        Button(
            onClick = vm::enviarContacto,
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text(if (state.isLoading) "Enviando..." else "Enviar")
        }

        /* -------------------------
         *   Mensaje de éxito
         * ------------------------- */
        if (state.enviado) {
            Text(
                "Mensaje enviado correctamente.",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
