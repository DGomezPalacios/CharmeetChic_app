package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charmeetchic_grupo2.ui.theme.CharmeetChicUI
import com.example.charmeetchic_grupo2.viewmodel.AuthViewModel
import com.example.charmeetchic_grupo2.domain.validation.validateEmail
import com.example.charmeetchic_grupo2.domain.validation.validateNotEmpty

@Composable
fun LoginScreen(
    onLoginOk: () -> Unit,
    onGoRegister: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    // Estados provenientes del ViewModel
    val isLoading = authViewModel.isLoading
    val loginSuccess = authViewModel.loginSuccess
    val errorMessage = authViewModel.errorMessage

    // Estados locales del formulario
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // VALIDACIONES usando tus Validators.kt
    val emailError = validateEmail(email)
    val passwordError = if (password.length < 6) "Mínimo 6 caracteres" else null
    val canLogin = emailError == null && passwordError == null

    // Navega cuando el login fue exitoso
    LaunchedEffect(loginSuccess) {
        if (loginSuccess) {
            onLoginOk()
            authViewModel.clearErrors()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            // EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                isError = emailError != null,
                supportingText = {
                    if (emailError != null) Text(emailError)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = CharmeetChicUI.textFieldColors
            )

            // PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                isError = passwordError != null,
                supportingText = {
                    if (passwordError != null) Text(passwordError)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = CharmeetChicUI.textFieldColors
            )

            // ERROR GENERAL desde ViewModel
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // BOTÓN LOGIN
            Button(
                onClick = {
                    authViewModel.login(email, password)
                },
                enabled = !isLoading && canLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = CharmeetChicUI.buttonColors,
                shape = MaterialTheme.shapes.medium
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp)
                    )
                } else {
                    Text("Ingresar")
                }
            }

            OutlinedButton(
                onClick = onGoRegister,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("¿No tienes cuenta? Regístrate")
            }

            Text(
                text = "Tu información está segura 💎",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
