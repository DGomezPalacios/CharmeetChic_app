package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.R
import com.example.charmeetchic_grupo2.model.Product

object ProductRepository {
    private val products = listOf(
        Product(
            id = "p1",
            name = "Collar corazón",
            price = 24990,
            imageUrl = null,
            category = "Collar Corazón",
            imageRes = R.drawable.collar_corazon
        ),
        Product(
            id = "p2",
            name = "Aros Abanicos",
            price = 19990,
            imageUrl = null,
            category = "Aros",
            imageRes = R.drawable.aros_abanicos
        ),
        Product(
            id = "p3",
            name = "Pulsera de Plata",
            price = 14990,
            imageUrl = null,
            category = "Pulseras",
            imageRes = R.drawable.pulsera_plata
        ),
        Product(
            id = "p4",
            name = "Anillo Luna",
            price = 22990,
            imageUrl = null,
            category = "Anillos",
            imageRes = R.drawable.anillo_luna
        ),
    )

    fun getAll(): List<Product> = products

    fun searchByName(q: String): List<Product> =
        products.filter { it.name.contains(q, ignoreCase = true) }

    fun byCategory(cat: String): List<Product> =
        products.filter { it.category.equals(cat, ignoreCase = true) }
}

