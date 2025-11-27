package com.example.charmeetchic_grupo2.viewmodel

data class ContactState(
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val message: String = "",
    val serviceType: String = "",
    val imageUrl: String = "",

    // ---- Errores de validación ----
    val errName: String? = null,
    val errEmail: String? = null,
    val errPhone: String? = null,
    val errMessage: String? = null,
    val errImageUrl: String? = null,

    // ---- Estado de envío ----
    val isLoading: Boolean = false,
    val enviado: Boolean = false
)
