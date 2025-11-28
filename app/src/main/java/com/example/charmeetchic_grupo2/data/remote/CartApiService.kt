package com.example.charmeetchic_grupo2.data.remote

import com.example.charmeetchic_grupo2.model.CartResponse
import retrofit2.http.*

interface CartApiService {

    // Obtener carrito del usuario
    @GET("api/compras/carrito-completo/{userId}")
    suspend fun obtenerCarrito(
        @Path("userId") userId: Long
    ): CartResponse

    // Agregar producto al carrito
    @POST("api/compras/carrito/agregar")
    suspend fun agregarAlCarrito(
        @Query("usuarioId") usuarioId: Long,
        @Query("productoId") productoId: Long
    )

    // Actualizar cantidad de un producto del carrito
    @PUT("api/compras/carrito/{usuarioId}/producto/{productoId}")
    suspend fun actualizarCarrito(
        @Path("usuarioId") usuarioId: Long,
        @Path("productoId") productoId: Long,
        @Query("cantidad") cantidad: Int
    )

    // Eliminar producto del carrito
    @DELETE("api/compras/carrito/{usuarioId}/producto/{productoId}")
    suspend fun eliminarDelCarrito(
        @Path("usuarioId") usuarioId: Long,
        @Path("productoId") productoId: Long
    )

    // Vaciar carrito completo
    @DELETE("api/compras/carrito/{usuarioId}")
    suspend fun vaciarCarrito(
        @Path("usuarioId") usuarioId: Long
    )

    // Confirmar compra → convierte el carrito en compra real
    @POST("api/compras/carrito/{usuarioId}/confirmar")
    suspend fun confirmarCompra(
        @Path("usuarioId") usuarioId: Long
    )
}
