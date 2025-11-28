package com.example.charmeetchic_grupo2.model

data class ProductDTO(
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int?
)