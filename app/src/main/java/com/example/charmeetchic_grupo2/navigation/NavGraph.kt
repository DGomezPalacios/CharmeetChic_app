package com.example.charmeetchic_grupo2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.charmeetchic_grupo2.ui.screen.HomeScreen
import com.example.charmeetchic_grupo2.ui.screen.LoginScreen
import com.example.charmeetchic_grupo2.ui.screen.RegistrationScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    // Define el flujo principal de navegación
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
    }
}
