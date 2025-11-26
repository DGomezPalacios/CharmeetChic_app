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
    var phone by mutableStateOf("")
    var message by mutableStateOf("")
    var serviceType by mutableStateOf("")
    var imageUrl by mutableStateOf("")

    // Estados de UI
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var successMessage by mutableStateOf<String?>(null)
        private set

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

    fun enviarContacto() {
        if (!validate()) return

        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null
                successMessage = null

                val request = ContactRequest(
                    name = name,
                    email = email,
                    phone = phone.ifBlank { null },
                    message = message,
                    serviceType = serviceType.ifBlank { null },
                    imageUrl = imageUrl.ifBlank { null }
                )

                val response: ContactResponse = repository.enviarContacto(request)

                successMessage = response.message ?: "Mensaje enviado correctamente."

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
