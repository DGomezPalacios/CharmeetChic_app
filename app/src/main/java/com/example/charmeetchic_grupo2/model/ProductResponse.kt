package com.example.charmeetchic_grupo2.network.dto

import com.example.charmeetchic_grupo2.model.Product

data class ProductResponse(
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val stock: Int,
    val material: String,
    val peso: Double,
    val medidas: String,
    val categoriaId: Long
)

fun ProductResponse.toUI(): Product {
    return Product(
        id = id,
        nombre = nombre,
        precio = precio,
        categoria = categoriaId.toString(), // simple
        imagenUrl = null,
        imageRes = null
    )
}
