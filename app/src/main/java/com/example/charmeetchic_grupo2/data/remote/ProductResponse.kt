package com.example.charmeetchic_grupo2.data.remote

data class ProductResponse(
    val id: Long,
    val nombre: String,
    val precio: Double,
    val categoria: String,
    val imagenUrl: String?
)
