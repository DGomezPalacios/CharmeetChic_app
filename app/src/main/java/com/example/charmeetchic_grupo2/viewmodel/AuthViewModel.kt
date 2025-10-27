package com.example.charmeetchic_grupo2.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    val nameError: Boolean
        get() = name.isNotBlank() && name.length < 3

    val emailError: Boolean
        get() = email.isNotBlank() && !email.contains("@")

    val passwordError: Boolean
        get() = password.isNotBlank() && password.length < 6

    fun canLogin(): Boolean {
        return email.contains("@") && password.length >= 6
    }

    fun canRegister(): Boolean {
        return name.length >= 3 && email.contains("@") && password.length >= 6
    }
}
