package com.example.charmeetchic_grupo2.domain.validation

import android.util.Patterns

/**
 * Valida nombres: obligatorio, solo letras (incluye tildes y ñ) y espacios.
 * Retorna un mensaje de error o null si es válido.
 */
fun validateNameLettersOnly(nombre: String): String? {
    if (nombre.isBlank()) return "El nombre es obligatorio"
    val regex = Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$")
    return if (!regex.matches(nombre)) "Solo se aceptan letras y espacios" else null
}

  //Valida correos
fun validateEmail(email: String): String? {
    if (email.isBlank()) return "El correo es obligatorio"
    val ok = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    return if (!ok) "Formato de correo inválido" else null
}

// Valida teléfonos
fun validatePhoneDigitsOnly(phone: String, minLen: Int = 9): String? {
    if (phone.isBlank()) return "El teléfono es obligatorio"
    if (!phone.all { it.isDigit() }) return "Solo se aceptan números"
    if (phone.length < minLen) return "Debe tener al menos $minLen dígitos"
    return null
}

// Valida que no esté vacio
fun validateNotEmpty(text: String): String? =
    if (text.isBlank()) "Campo obligatorio" else null

