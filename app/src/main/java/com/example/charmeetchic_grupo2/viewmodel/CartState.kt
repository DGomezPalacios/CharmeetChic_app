package com.example.charmeetchic_grupo2.viewmodel

import com.example.charmeetchic_grupo2.model.CartItemDTO

data class CartState(
    val items: List<CartItemDTO> = emptyList(),
    val total: Double = 0.0,
    val cargando: Boolean = false,
    val mensaje: String? = null
)
