package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.data.remote.ProductApiService
import com.example.charmeetchic_grupo2.data.remote.RetrofitInstance
import com.example.charmeetchic_grupo2.data.remote.toProduct
import com.example.charmeetchic_grupo2.model.Product

class ProductRepository {

    private val api = RetrofitInstance
        .productoRetrofit
        .create(ProductApiService::class.java)

    // Obtener todos los productos
    suspend fun listarTodos(): List<Product> =
        api.listarTodos().map { it.toProduct() }

    // Buscar por ID
    suspend fun buscarPorId(id: Long): Product =
        api.buscarPorId(id).toProduct()

    // Buscar por nombre
    suspend fun buscarPorNombre(nombre: String): List<Product> =
        api.buscarPorNombre(nombre).map { it.toProduct() }
}
