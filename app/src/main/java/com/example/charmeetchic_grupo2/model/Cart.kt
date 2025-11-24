package com.example.charmeetchic_grupo2.model

data class Cart(
    val items: List<CartItem> = emptyList()
) {
    // Total del carrito sumando subtotales
    val total: Int
        get() = items.sumOf { it.subtotal }

    // Total de unidades sumando las cantidades (qty)
    val totalItems: Int
        get() = items.sumOf { it.qty }
}
