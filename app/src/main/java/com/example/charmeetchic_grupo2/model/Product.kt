package com.example.charmeetchic_grupo2.model

data class Product(
    val id: String,
    val name: String,
    val price: Int,
    val imageUrl: String? = null,
    val description: String = "",
    val category: String = ""
)
