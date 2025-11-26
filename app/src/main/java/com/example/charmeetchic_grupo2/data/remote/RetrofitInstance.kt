package com.example.charmeetchic_grupo2.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    // CONTACTO (Microservicio Contacto)
    private const val BASE_URL_CONTACTO = "http://10.0.2.2:8091/"
    val contactoRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_CONTACTO)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    // PRODUCTO (Microservicio Producto)
    private const val BASE_URL_PRODUCTO = "http://10.0.2.2:8088/"
    val productoRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_PRODUCTO)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    // COMPRAS (Microservicio Compras)
    private const val BASE_URL_COMPRAS = "http://10.0.2.2:8083/"
    val comprasRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_COMPRAS)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
