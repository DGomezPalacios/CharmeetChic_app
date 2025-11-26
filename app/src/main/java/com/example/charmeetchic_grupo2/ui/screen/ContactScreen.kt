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

    val isLoading = vm.isLoading
    val error = vm.errorMessage
    val success = vm.successMessage

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text("Contacto", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = vm.name,
            onValueChange = { vm.name = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vm.email,
            onValueChange = { vm.email = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vm.phone,
            onValueChange = { vm.phone = it },
            label = { Text("Teléfono (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vm.serviceType,
            onValueChange = { vm.serviceType = it },
            label = { Text("Tipo de servicio (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vm.imageUrl,
            onValueChange = { vm.imageUrl = it },
            label = { Text("Imagen URL (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = vm.message,
            onValueChange = { vm.message = it },
            label = { Text("Mensaje") },
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        )

        Button(
            onClick = vm::enviarContacto,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Enviando..." else "Enviar")
        }

        // Mostrar errores
        error?.let {
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        // Mostrar éxito
        success?.let {
            Text(it, color = MaterialTheme.colorScheme.primary)
        }
    }
}
