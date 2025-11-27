package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.data.remote.toProduct
import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.network.ApiClient
import com.example.charmeetchic_grupo2.network.ProductApi

class ProductRepository {

    private val api = ApiClient.retrofit.create(ProductApi::class.java)

    suspend fun getAllProducts(): List<Product> {
        return api.getProducts().map { it.toProduct() }
    }
}
