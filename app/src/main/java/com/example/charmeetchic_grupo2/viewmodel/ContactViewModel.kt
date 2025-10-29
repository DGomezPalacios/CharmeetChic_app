package com.example.charmeetchic_grupo2.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.charmeetchic_grupo2.domain.validation.validateEmail
import com.example.charmeetchic_grupo2.domain.validation.validateNameLettersOnly

data class ContactState(
    val nombre: String = "",
    val correo: String = "",
    val mensaje: String = "",
    val errNombre: String? = null,
    val errCorreo: String? = null,
    val errMensaje: String? = null,
    val enviado: Boolean = false
)

class ContactViewModel: ViewModel() {
    private val _state = MutableStateFlow(ContactState())
    val state: StateFlow<ContactState> = _state

    fun onNombreChange(v: String) {
        _state.value = _state.value.copy(nombre = v, errNombre = validateNameLettersOnly(v))
    }
    fun onCorreoChange(v: String) {
        _state.value = _state.value.copy(correo = v, errCorreo = validateEmail(v))
    }
    fun onMensajeChange(v: String) {
        val err = if (v.isBlank()) "El mensaje es obligatorio" else null
        _state.value = _state.value.copy(mensaje = v, errMensaje = err)
    }

    fun submit() {
        val s = _state.value
        val e1 = validateNameLettersOnly(s.nombre)
        val e2 = validateEmail(s.correo)
        val e3 = if (s.mensaje.isBlank()) "El mensaje es obligatorio" else null
        if (e1 != null || e2 != null || e3 != null) {
            _state.value = s.copy(errNombre = e1, errCorreo = e2, errMensaje = e3)
            return
        }
        _state.value = s.copy(enviado = true)
    }

    fun reset() { _state.value = ContactState() }
}
