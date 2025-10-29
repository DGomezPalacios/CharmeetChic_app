package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.model.Product

object ProductRepository {
    private val products = listOf(
        Product("p1", "Collar corazón", 24990, imageUrl = null, category = "Collares"),
        Product("p2", "Aros perla", 19990, imageUrl = null, category = "Aros"),
        Product("p3", "Pulsera minimal", 14990, imageUrl = null, category = "Pulseras"),
        Product("p4", "Anillo plata", 22990, imageUrl = null, category = "Anillos"),
    )

    fun getAll(): List<Product> = products
    fun searchByName(q: String): List<Product> =
        products.filter { it.name.contains(q, ignoreCase = true) }
    fun byCategory(cat: String): List<Product> =
        products.filter { it.category.equals(cat, ignoreCase = true) }
}
