package com.example.charmeetchic_grupo2.network

import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.model.ProductRequest
import retrofit2.http.*

interface ProductApi {

    @GET("productos")
    suspend fun getProducts(): List<Product>

    @GET("productos/{id}")
    suspend fun getProductById(@Path("id") id: Long): Product

    @POST("productos")
    suspend fun createProduct(@Body request: ProductRequest): Product

    @DELETE("productos/{id}")
    suspend fun deleteProduct(@Path("id") id: Long)
}
