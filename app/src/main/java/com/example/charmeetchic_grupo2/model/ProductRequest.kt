package com.example.charmeetchic_grupo2.model

data class ProductRequest(
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val material: String,
    val peso: Double?,
    val medidas: String,
    val categoriaId: Long,
    val imagenUrl: String? = null
)
