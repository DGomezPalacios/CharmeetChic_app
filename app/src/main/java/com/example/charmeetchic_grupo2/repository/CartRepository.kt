package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.data.remote.CartApiService
import com.example.charmeetchic_grupo2.data.remote.RetrofitInstance
import com.example.charmeetchic_grupo2.model.Compras

class CartRepository {

    private val api = RetrofitInstance
        .comprasRetrofit
        .create(CartApiService::class.java)

    suspend fun obtenerCarrito(): List<Compras> {
        return api.obtenerCarrito()
    }

    suspend fun agregarAlCarrito(compra: Compras): Compras {
        return api.agregarAlCarrito(compra)
    }

    suspend fun actualizarCarrito(id: Long, compra: Compras): Compras {
        return api.actualizarCarrito(id, compra)
    }

    suspend fun eliminarDelCarrito(id: Long) {
        api.eliminarDelCarrito(id)
    }
}
