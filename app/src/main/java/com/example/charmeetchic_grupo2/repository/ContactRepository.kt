package com.example.charmeetchic_grupo2.repository

import com.example.charmeetchic_grupo2.data.remote.ContactApiService
import com.example.charmeetchic_grupo2.data.remote.RetrofitInstance
import com.example.charmeetchic_grupo2.model.ContactRequest
import com.example.charmeetchic_grupo2.model.ContactResponse

class ContactRepository {

    private val api = RetrofitInstance
        .contactoRetrofit
        .create(ContactApiService::class.java)

    suspend fun enviarContacto(request: ContactRequest): ContactResponse {
        return api.enviarContacto(request)
    }
}
