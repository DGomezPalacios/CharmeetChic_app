package com.example.charmeetchic_grupo2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.LoginRequest
import com.example.charmeetchic_grupo2.model.RegisterRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    // Campos del formulario
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    // Estados
    var isLoading by mutableStateOf(false)
    var loginSuccess by mutableStateOf(false)
    var registerSuccess by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    // Nuevo: ¿este usuario es admin?
    var isAdmin by mutableStateOf(false)
        private set

    // Validaciones
    val nameError get() = name.isNotBlank() && name.length < 3
    val emailError get() = email.isNotBlank() && !email.contains("@")
    val passwordError get() = password.isNotBlank() && password.length < 6

    fun canLogin(email: String = this.email, password: String = this.password): Boolean {
        return email.contains("@") && password.length >= 6
    }

    fun canRegister(name: String = this.name, email: String = this.email, password: String = this.password): Boolean {
        return name.length >= 3 && email.contains("@") && password.length >= 6
    }

    fun login(email: String, password: String) {
        this.email = email
        this.password = password

        if (!canLogin(email, password)) {
            errorMessage = "Revisa tu correo y contraseña."
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            loginSuccess = false
            isAdmin = false    // reset

            // Simulación (API futuro)
            val request = LoginRequest(email, password)
            delay(1000)

            // REGLA: usuario admin fijo
            if (email == "admin@admin.com" && password == "admin123") {
                isAdmin = true
            }

            loginSuccess = true
            isLoading = false
        }
    }

    fun register(name: String, email: String, password: String) {
        this.name = name
        this.email = email
        this.password = password

        if (!canRegister(name, email, password)) {
            errorMessage = "Datos no válidos. Revisa nombre, correo y contraseña."
            return
        }

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            registerSuccess = false

            // Simulación API
            val request = RegisterRequest(name, email, password)
            delay(1000)

            registerSuccess = true
            isLoading = false
        }
    }

    fun clearErrors() {
        errorMessage = null
    }
}
