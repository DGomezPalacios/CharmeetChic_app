package com.example.charmeetchic_grupo2.model

data class CartResponse(
    val compraId: Long,
    val usuarioId: Long,
    val estado: String,
    val total: Double,
    val items: List<CartItemDTO>
)