package com.example.charmeetchic_grupo2.model

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val phone: String = "",
    val isLogged: Boolean = false
)
