package com.example.charmeetchic_grupo2.navigation

sealed class Routes(val route: String) {
    // 🏠 Sección principal
    object Home : Routes("home")

    // 🧾 Autenticación
    object Login : Routes("login")
    object Register : Routes("register")
    object Profile : Routes("profile")

    // 💎 Tienda
    object Catalog : Routes("catalog")
    object Cart : Routes("cart")
    object RepareAndPers : Routes("repare_and_pers")

    // 📞 Información y contacto
    object About : Routes("about")
    object Contact : Routes("contact")
}
