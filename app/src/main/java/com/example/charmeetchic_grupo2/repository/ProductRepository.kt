package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.network.ApiClient
import com.example.charmeetchic_grupo2.network.dto.ProductResponse

class ProductRepository {

    private val api = ApiClient.productApi

    suspend fun getAllProducts(): List<Product> {
        val response = api.getProducts()
        return response.map { it.toUI() }
    }
}
