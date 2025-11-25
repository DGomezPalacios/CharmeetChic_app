package com.example.charmeetchic_grupo2.data.remote

import com.example.charmeetchic_grupo2.model.Compras
import retrofit2.http.*

interface CartApiService {

    @GET("api/compras")
    suspend fun obtenerCarrito(): List<Compras>

    @POST("api/compras")
    suspend fun agregarAlCarrito(@Body compra: Compras): Compras

    @PUT("api/compras/{id}")
    suspend fun actualizarCarrito(
        @Path("id") id: Long,
        @Body compra: Compras
    ): Compras

    @DELETE("api/compras/{id}")
    suspend fun eliminarDelCarrito(@Path("id") id: Long)
}
