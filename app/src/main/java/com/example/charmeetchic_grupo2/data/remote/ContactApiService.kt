package com.example.charmeetchic_grupo2.data.remote

import com.example.charmeetchic_grupo2.model.ContactRequest
import com.example.charmeetchic_grupo2.model.ContactResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ContactApiService {

    @POST("api/contacto")
    suspend fun enviarContacto(
        @Body request: ContactRequest
    ): ContactResponse
}
