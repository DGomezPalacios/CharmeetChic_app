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

        // Imagen por URL (si viene del backend)
        imagenUrl = null,     // <--- por ahora null, luego lo activamos

        // Imagen local automática
        imageRes = run {
            val n = nombre.lowercase()

            when {
                n.contains("collar") && n.contains("minimalista") ->
                    R.drawable.collar_dorado_minimalista

                n.contains("aros") && n.contains("vintage") ->
                    R.drawable.aros_perla_vintage

                else -> null
            }
        }
    )
}


