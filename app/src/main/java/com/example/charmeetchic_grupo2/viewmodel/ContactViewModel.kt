package com.example.charmeetchic_grupo2.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.model.ContactRequest
import com.example.charmeetchic_grupo2.model.ContactResponse
import com.example.charmeetchic_grupo2.repository.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(
    private val repository: ContactRepository = ContactRepository()
) : ViewModel() {

    // Campos del formulario
    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var message by mutableStateOf("")

    // Estados de la UI
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var successMessage by mutableStateOf<String?>(null)
        private set


    // ⭐ Validaciones simples
    private fun validate(): Boolean {
        if (name.isBlank()) {
            errorMessage = "El nombre es obligatorio"
            return false
        }
        if (!email.contains("@")) {
            errorMessage = "Correo inválido"
            return false
        }
        if (message.isBlank()) {
            errorMessage = "El mensaje no puede estar vacío"
            return false
        }

        return true
    }


    // ⭐ Función principal: enviar el formulario
    fun enviarContacto() {
        if (!validate()) return

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                successMessage = null

                val request = ContactRequest(
                    nombre = name,
                    email = email,
                    mensaje = message
                )

                val response: ContactResponse = repository.enviarContacto(request)

                successMessage = response.message ?: "Enviado correctamente."

            } catch (e: Exception) {
                errorMessage = "Error al enviar: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun limpiarMensajes() {
        errorMessage = null
        successMessage = null
    }
}
