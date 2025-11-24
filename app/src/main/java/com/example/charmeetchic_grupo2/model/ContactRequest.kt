package com.example.charmeetchic_grupo2.model

data class ContactRequest(
    val name: String,
    val email: String,
    val phone: String?,
    val message: String,
    val serviceType: String?,
    val imageUrl: String?
)
