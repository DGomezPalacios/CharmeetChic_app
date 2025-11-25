package com.example.charmeetchic_grupo2.data.remote

import com.example.charmeetchic_grupo2.model.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApiService {

    // 1. Obtener todos los productos
    @GET("api/productos")
    suspend fun listarTodos(): List<Product>

    // 2. Obtener producto por ID
    @GET("api/productos/{id}")
    suspend fun buscarPorId(@Path("id") id: Long): Product

    // 3. Buscar productos por nombre
    @GET("api/productos/buscar")
    suspend fun buscarPorNombre(@Query("nombre") nombre: String): List<Product>
}
