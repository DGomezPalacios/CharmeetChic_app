package com.example.charmeetchic_grupo2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.charmeetchic_grupo2.ui.screen.HomeScreen
import com.example.charmeetchic_grupo2.ui.screen.LoginScreen
import com.example.charmeetchic_grupo2.ui.screen.RegistrationScreen
import com.example.charmeetchic_grupo2.ui.screen.AboutUsScreen
import com.example.charmeetchic_grupo2.ui.screen.CatalogScreen
import com.example.charmeetchic_grupo2.ui.screen.CartScreen
import com.example.charmeetchic_grupo2.ui.screen.ContactScreen


@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route // Pantalla inicial
    ) {
        // 🏠 Home
        composable(Routes.Home.route) {
            HomeScreen(
                onGoCatalog = { navController.navigate(Routes.Catalog.route) },
                onGoLogin = { navController.navigate(Routes.Login.route) }
            )
        }

        // 🔐 Login
        composable(Routes.Login.route) {
            LoginScreen(
                onLoginOk = { navController.navigate(Routes.Home.route) },
                onGoRegister = { navController.navigate(Routes.Register.route) }
            )
        }

        // 📝 Registro
        composable(Routes.Register.route) {
            RegistrationScreen(
                onGoLogin = { navController.navigate(Routes.Login.route) },
                onRegisterOk = { navController.navigate(Routes.Home.route) }
            )
        }
        // 🛍️ Catálogo
        composable(Routes.Catalog.route) {
            CatalogScreen()
        }

// 🧺 Carrito
        composable(Routes.Cart.route) {
            CartScreen()
        }

// ℹ️ About us
        composable(Routes.About.route) {
            AboutUsScreen()
        }

// ✉️ Contacto
        composable(Routes.Contact.route) {
            ContactScreen()
        }
    }
}
