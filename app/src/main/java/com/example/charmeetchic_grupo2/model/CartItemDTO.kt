package com.example.charmeetchic_grupo2.model

data class CartItemDTO(
    val id: Long,
    val productoId: Long,
    val cantidad: Int,
    val producto: ProductDTO
)
