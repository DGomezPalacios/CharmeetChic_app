package com.example.charmeetchic_grupo2.model

data class Cart(
    val items: List<CartItem> = emptyList()
) {
    val total: Double
        get() = items.sumOf { it.subtotal }
}
