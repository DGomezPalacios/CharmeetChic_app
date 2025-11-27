package com.example.charmeetchic_grupo2.model

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

// Convertir DTO → Modelo local
fun ProductResponse.toUI(): Product {
    return Product(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        precio = precio,
        stock = stock,
        material = material,
        peso = peso,
        medidas = medidas,
        categoriaId = categoriaId,
        imagenUrl = null,
        imageRes = null
    )
}

