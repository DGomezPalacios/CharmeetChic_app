package com.example.charmeetchic_grupo2.data.remote

import com.example.charmeetchic_grupo2.R
import com.example.charmeetchic_grupo2.model.Product

fun ProductResponse.toProduct(): Product {

    val imageRes = when (nombre.lowercase()) {
        "collar minimalista" -> R.drawable.collar_dorado_minimalista
        "aros vintage" -> R.drawable.aros_perla_vintage
        else -> null
    }

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
        imageRes = imageRes
    )
}
