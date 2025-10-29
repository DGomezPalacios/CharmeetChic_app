package com.example.charmeetchic_grupo2.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.charmeetchic_grupo2.domain.validation.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RepareUiState(
    val photo: Uri? = null,
    val note: String = "",
    // errores
    val photoError: String? = null,
    val noteError: String? = null,
    // UI
    val canSend: Boolean = false,
    val sentOk: Boolean = false
)

class RepareAndPersViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(RepareUiState())
    val uiState: StateFlow<RepareUiState> = _uiState

    fun onPhotoPicked(uri: Uri?) {
        _uiState.update { it.copy(photo = uri) }
        validateAll()
    }

    fun onNoteChange(value: String) {
        _uiState.update { it.copy(note = value) }
        validateAll()
    }

    private fun validateAll() {
        val s = _uiState.value
        val noteErr = validateNotEmpty(s.note)
        val photoErr = if (s.photo == null) "Debes adjuntar una imagen" else null
        val ok = noteErr == null && photoErr == null
        _uiState.update { it.copy(noteError = noteErr, photoError = photoErr, canSend = ok) }
    }

    fun submit() = viewModelScope.launch {
        validateAll()
        if (_uiState.value.canSend) {
            // TODO: llamar repositorio si aplica
            _uiState.update { it.copy(sentOk = true) }
        }
    }
}