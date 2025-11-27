package com.example.charmeetchic_grupo2.network

import com.example.charmeetchic_grupo2.data.remote.ProductResponse
import com.example.charmeetchic_grupo2.model.ProductRequest
import retrofit2.http.*

interface ProductApi {

    @GET("productos")
    suspend fun getProducts(): List<ProductResponse>

    @GET("productos/{id}")
    suspend fun getProductById(@Path("id") id: Long): ProductResponse

    @GET("productos/buscar")
    suspend fun searchProducts(
        @Query("nombre") nombre: String
    ): List<ProductResponse>

    @POST("productos")
    suspend fun createProduct(@Body request: ProductRequest): ProductResponse

    @DELETE("productos/{id}")
    suspend fun deleteProduct(@Path("id") id: Long)
    @PUT("productos/{id}")
    suspend fun updateProduct(
        @Path("id") id: Long,
        @Body request: ProductRequest
    ): ProductResponse

}
