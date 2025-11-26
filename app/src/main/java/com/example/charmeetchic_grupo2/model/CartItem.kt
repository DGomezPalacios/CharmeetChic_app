package com.example.charmeetchic_grupo2.model

data class CartItem(
    val product: Product,
    val qty: Int
) {
    val subtotal: Double get() = product.precio * qty
}
