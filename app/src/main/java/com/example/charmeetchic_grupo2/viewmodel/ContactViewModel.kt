package com.example.charmeetchic_grupo2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.domain.validation.*
import com.example.charmeetchic_grupo2.model.ContactRequest
import com.example.charmeetchic_grupo2.model.ContactResponse
import com.example.charmeetchic_grupo2.repository.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(
    private val repository: ContactRepository = ContactRepository()
) : ViewModel() {

    // 🔥 ESTADO OBSERVABLE POR COMPOSE
    var state by mutableStateOf(ContactState())
        private set

    /* ----------------------------------------------------
     *  ACTUALIZADORES DE CAMPOS (validan en tiempo real)
     * ---------------------------------------------------- */

    fun onNameChange(value: String) {
        state = state.copy(
            name = value,
            errName = validateNameLettersOnly(value)
        )
    }

    fun onEmailChange(value: String) {
        state = state.copy(
            email = value,
            errEmail = validateEmail(value)
        )
    }

    fun onPhoneChange(value: String) {
        state = state.copy(
            phone = value,
            errPhone = validatePhoneDigitsOnly(value)
        )
    }

    fun onMessageChange(value: String) {
        state = state.copy(
            message = value,
            errMessage = validateMessageMin5(value)
        )
    }

    fun onServiceTypeChange(value: String) {
        state = state.copy(serviceType = value)
    }

    fun onImageUrlChange(value: String) {
        state = state.copy(
            imageUrl = value,
            errImageUrl = validateOptionalUrl(value)
        )
    }


    /* ----------------------------------------------------
     *  VALIDACIÓN COMPLETA ANTES DE ENVIAR
     * ---------------------------------------------------- */
    private fun validateAll(): Boolean {
        val errName = validateNameLettersOnly(state.name)
        val errEmail = validateEmail(state.email)
        val errPhone = validatePhoneDigitsOnly(state.phone)
        val errMessage = validateMessageMin5(state.message)
        val errImage = validateOptionalUrl(state.imageUrl)

        state = state.copy(
            errName = errName,
            errEmail = errEmail,
            errPhone = errPhone,
            errMessage = errMessage,
            errImageUrl = errImage
        )

        return listOf(errName, errEmail, errPhone, errMessage, errImage)
            .all { it == null }
    }


    /* ----------------------------------------------------
     *           ENVIAR FORMULARIO AL BACKEND
     * ---------------------------------------------------- */
    fun enviarContacto() {
        if (!validateAll()) return

        viewModelScope.launch {
            try {
                state = state.copy(
                    isLoading = true,
                    enviado = false
                )

                val request = ContactRequest(
                    name = state.name,
                    email = state.email,
                    phone = state.phone.ifBlank { null },
                    message = state.message,
                    serviceType = state.serviceType.ifBlank { null },
                    imageUrl = state.imageUrl.ifBlank { null }
                )

                val response: ContactResponse = repository.enviarContacto(request)

                // éxito
                state = state.copy(
                    isLoading = false,
                    enviado = true
                )

            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    errMessage = "Error al enviar: ${e.message}"
                )
            }
        }
    }


    /* ----------------------------------------------------
     *             RESET COMPLETO DEL FORMULARIO
     * ---------------------------------------------------- */
    fun resetForm() {
        state = ContactState()
    }
}
