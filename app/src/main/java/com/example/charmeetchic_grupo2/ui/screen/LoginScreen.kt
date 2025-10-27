package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charmeetchic_grupo2.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onLoginOk: () -> Unit,
    onGoRegister: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    val bg = MaterialTheme.colorScheme.secondaryContainer

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(16.dp))

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

            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                label = { Text("Contraseña") },
                isError = viewModel.passwordError,
                supportingText = {
                    if (viewModel.passwordError) Text("Mínimo 6 caracteres")
                },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    if (viewModel.canLogin()) onLoginOk()
                },
                enabled = viewModel.canLogin(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar")
            }

            Spacer(Modifier.height(8.dp))

            TextButton(onClick = onGoRegister) {
                Text("¿No tienes cuenta? Regístrate")
            }
        }
    }
}
