package com.example.charmeetchic_grupo2.data.remote

import com.example.charmeetchic_grupo2.R
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
        imageRes = when {
            nombre.contains("collar", ignoreCase = true) &&
                    nombre.contains("minimalista", ignoreCase = true) ->
                R.drawable.collar_dorado_minimalista

            nombre.contains("aros", ignoreCase = true) &&
                    nombre.contains("vintage", ignoreCase = true) ->
                R.drawable.aros_perla_vintage

            else -> null
        }

    )
}

