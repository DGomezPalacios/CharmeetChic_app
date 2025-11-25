package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.data.remote.ProductApiService
import com.example.charmeetchic_grupo2.data.remote.RetrofitInstance
import com.example.charmeetchic_grupo2.model.Product

class ProductoRepository {

    // Usa el Retrofit del microservicio PRODUCTO (puerto 8088)
    private val api = RetrofitInstance
        .productoRetrofit
        .create(ProductApiService::class.java)

    // Obtener todos los productos
    suspend fun listarTodos(): List<Product> = api.listarTodos()

    // Buscar por ID
    suspend fun buscarPorId(id: Long): Product = api.buscarPorId(id)

    // Buscar por nombre
    suspend fun buscarPorNombre(nombre: String): List<Product> =
        api.buscarPorNombre(nombre)
}
