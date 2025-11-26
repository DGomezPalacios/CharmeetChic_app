package com.example.charmeetchic_grupo2.data.remote

import com.example.charmeetchic_grupo2.model.Product

// Extensión que convierte ProductResponse en Product (modelo local)
fun ProductResponse.toProduct(): Product =
    Product(
        id = id,                // Long ✔
        nombre = nombre,        // String ✔
        precio = precio,        // Double ✔
        categoria = categoria,  // String ✔
        imagenUrl = imagenUrl   // String? ✔
    )
