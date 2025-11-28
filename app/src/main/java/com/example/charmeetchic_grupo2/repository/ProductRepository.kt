package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.data.remote.toUI
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.model.ProductRequest
import com.example.charmeetchic_grupo2.network.ApiClient
import com.example.charmeetchic_grupo2.network.ProductApi

class ProductRepository(
    private val api: ProductApi = ApiClient.retrofit.create(ProductApi::class.java)
) {

    suspend fun getAllProducts() = api.getProducts().map { it.toUI() }

    suspend fun createProduct(request: ProductRequest) =
        api.createProduct(request).toUI()

    suspend fun updateProduct(id: Long, request: ProductRequest) =
        api.updateProduct(id, request).toUI()

    suspend fun deleteProduct(id: Long) = api.deleteProduct(id)
}


