package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charmeetchic_grupo2.viewmodel.AuthViewModel

@Composable
fun RegistrationScreen(
    onGoLogin: () -> Unit,    // Navegar al Login
    onRegisterOk: () -> Unit, // Accion al registrarse correctamente
    viewModel: AuthViewModel = viewModel()
) {
    val bg = MaterialTheme.colorScheme.tertiaryContainer

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Registro de Usuario",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

            // Campo Nombre
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                label = { Text("Nombre completo") },
                isError = viewModel.nameError,
                supportingText = {
                    if (viewModel.nameError) Text("El nombre debe tener al menos 3 caracteres")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            // Campo Correo
            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                label = { Text("Correo electrónico") },
                isError = viewModel.emailError,
                supportingText = {
                    if (viewModel.emailError) Text("Formato de correo inválido")
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            // Campo Contraseña
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                label = { Text("Contraseña") },
                isError = viewModel.passwordError,
                supportingText = {
                    if (viewModel.passwordError) Text("Debe tener al menos 6 caracteres")
                },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            // Botón de Registro
            Button(
                onClick = {
                    if (viewModel.canRegister()) {
                        onRegisterOk()
                    }
                },
                enabled = viewModel.canRegister(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarse")
            }

            Spacer(Modifier.height(8.dp))

            // Enlace para volver al Login
            TextButton(onClick = onGoLogin) {
                Text("¿Ya tienes cuenta? Inicia sesión")
            }
        }
    }
}
