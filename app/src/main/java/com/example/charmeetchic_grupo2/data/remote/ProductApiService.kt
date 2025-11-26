package com.example.charmeetchic_grupo2.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {

    @GET("api/productos")
    suspend fun listarTodos(): List<ProductResponse>

    @GET("api/productos/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): ProductResponse

    @GET("api/productos/buscar")
    suspend fun buscarPorNombre(@Query("nombre") nombre: String): List<ProductResponse>
}
