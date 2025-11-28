package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.data.remote.CartApiService
import com.example.charmeetchic_grupo2.data.remote.RetrofitInstance
import com.example.charmeetchic_grupo2.model.CartResponse

class CartRepository {

    private val api = RetrofitInstance
        .comprasRetrofit
        .create(CartApiService::class.java)

    suspend fun obtenerCarrito(usuarioId: Long): CartResponse =
        api.obtenerCarrito(usuarioId)

    suspend fun agregarAlCarrito(usuarioId: Long, productoId: Long) =
        api.agregarAlCarrito(usuarioId, productoId)

    suspend fun actualizarCarrito(usuarioId: Long, productoId: Long, cantidad: Int) =
        api.actualizarCarrito(usuarioId, productoId, cantidad)

    suspend fun eliminarDelCarrito(usuarioId: Long, productoId: Long) =
        api.eliminarDelCarrito(usuarioId, productoId)

    suspend fun vaciarCarrito(usuarioId: Long) =
        api.vaciarCarrito(usuarioId)

    suspend fun confirmarCompra(usuarioId: Long) =
        api.confirmarCompra(usuarioId)
}
