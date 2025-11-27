package com.example.charmeetchic_grupo2.data.remote

import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.model.ProductResponse

// Extension que convierte ProductResponse → Product (modelo local)
fun ProductResponse.toProduct(): Product =
    Product(
        id = id,
        nombre = nombre,
        descripcion = descripcion,
        precio = precio,
        stock = stock,
        material = material,
        peso = peso,
        medidas = medidas,
        categoriaId = categoriaId
    )
