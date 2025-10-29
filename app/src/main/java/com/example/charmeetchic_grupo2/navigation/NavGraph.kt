package com.example.charmeetchic_grupo2.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.charmeetchic_grupo2.ui.components.AppTopBar
import com.example.charmeetchic_grupo2.ui.screen.*
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    cartVM: CartViewModel  // 🔹 ViewModel compartido del carrito
) {

    // Contador del carrito
    fun topBar() = @Composable {
        AppTopBar(
            onGoHome = { navController.navigate(Routes.Home.route) },
            onGoCatalog = { navController.navigate(Routes.Catalog.route) },
            onGoCart = { navController.navigate(Routes.Cart.route) },
            onGoRepare = { navController.navigate(Routes.RepareAndPers.route) },
            onGoAbout = { navController.navigate(Routes.About.route) },
            onGoContact = { navController.navigate(Routes.Contact.route) },
            onGoLogin = { navController.navigate(Routes.Login.route) },
            onGoRegister = { navController.navigate(Routes.Register.route) },
            cartVM = cartVM   // ✅ <-- ESTA es la línea clave para el contador
        )
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        // HOME
        composable(Routes.Home.route) {
            Scaffold(topBar = topBar()) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    HomeScreen(
                        onGoCatalog = { navController.navigate(Routes.Catalog.route) },
                        onGoLogin = { navController.navigate(Routes.Login.route) }
                    )
                }
            }
        }

        // CATALOG
        composable(Routes.Catalog.route) {
            Scaffold(topBar = topBar()) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    CatalogScreen(cartVM = cartVM)
                }
            }
        }

        // CART
        composable(Routes.Cart.route) {
            Scaffold(topBar = topBar()) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    CartScreen(cartVM = cartVM)
                }
            }
        }

        // REPARE AND PERS
        composable(Routes.RepareAndPers.route) {
            Scaffold(topBar = topBar()) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    RepareAndPersScreen(
                        onGoBack = { navController.popBackStack() },
                        onSendRequest = { }
                    )
                }
            }
        }

        // ABOUT US
        composable(Routes.About.route) {
            Scaffold(topBar = topBar()) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    AboutUsScreen()
                }
            }
        }

        // CONTACT
        composable(Routes.Contact.route) {
            Scaffold(topBar = topBar()) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    ContactScreen()
                }
            }
        }

        // LOGIN
        composable(Routes.Login.route) {
            Scaffold(topBar = topBar()) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    LoginScreen(
                        onLoginOk = { navController.navigate(Routes.Home.route) },
                        onGoRegister = { navController.navigate(Routes.Register.route) }
                    )
                }
            }
        }

        // REGISTER
        composable(Routes.Register.route) {
            Scaffold(topBar = topBar()) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    RegistrationScreen(
                        onGoLogin = { navController.navigate(Routes.Login.route) },
                        onRegisterOk = { navController.navigate(Routes.Home.route) }
                    )
                }
            }
        }
    }
}

