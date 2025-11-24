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
import com.example.charmeetchic_grupo2.domain.validation.validateNameLettersOnly

@Composable
fun RegistrationScreen(
    onGoLogin: () -> Unit,
    onRegisterOk: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    // Estados locales del formulario
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Estados del ViewModel
    val isLoading = authViewModel.isLoading
    val errorMessage = authViewModel.errorMessage
    val registerSuccess = authViewModel.registerSuccess

    // VALIDACIONES usando Validators.kt
    val nameError = validateNameLettersOnly(name)
    val emailError = validateEmail(email)
    val passwordError = if (password.length < 6) "Mínimo 6 caracteres" else null

    val canRegister = nameError == null && emailError == null && passwordError == null

    // Cuando el registro sea exitoso -> navega
    LaunchedEffect(registerSuccess) {
        if (registerSuccess) {
            onRegisterOk()
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
                text = "Registro de Usuario",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            // NOMBRE
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre completo") },
                isError = nameError != null,
                supportingText = {
                    if (nameError != null) Text(nameError)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = CharmeetChicUI.textFieldColors
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

            // ERROR GENERAL DESDE VIEWMODEL
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // BOTÓN REGISTRO
            Button(
                onClick = { authViewModel.register(name, email, password) },
                enabled = !isLoading && canRegister,
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
                    Text("Registrarse")
                }
            }

            OutlinedButton(
                onClick = onGoLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("¿Ya tienes cuenta? Inicia sesión")
            }

            Text(
                text = "Tu información se mantendrá protegida",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
