package com.example.charmeetchic_grupo2.model

data class Product(
    val id: Long,
    val nombre: String,
    val precio: Double,
    val categoria: String,
    val imagenUrl: String? = null,
    val imageRes: Int? = null
)
