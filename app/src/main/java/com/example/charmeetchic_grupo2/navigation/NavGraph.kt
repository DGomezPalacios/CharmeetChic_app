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
    cartVM: CartViewModel
) {
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
            onGoAdmin = { navController.navigate(Routes.AdminProducts.route) },
            cartVM = cartVM
        )
    }

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {

        composable(Routes.Home.route) {
            Scaffold(topBar = topBar()) { padding ->
                Box(Modifier.padding(padding)) {
                    HomeScreen(
                        onGoCatalog = { navController.navigate(Routes.Catalog.route) },
                        onGoLogin = { navController.navigate(Routes.Login.route) }
                    )
                }
            }
        }

        composable(Routes.Catalog.route) {
            Scaffold(topBar = topBar()) { padding ->
                Box(Modifier.padding(padding)) {
                    CatalogScreen(cartVM = cartVM)
                }
            }
        }

        composable(Routes.Cart.route) {
            Scaffold(topBar = topBar()) { padding ->
                Box(Modifier.padding(padding)) {
                    CartScreen(cartVM = cartVM)
                }
            }
        }

        composable(Routes.RepareAndPers.route) {
            Scaffold(topBar = topBar()) { padding ->
                Box(Modifier.padding(padding)) {
                    RepareAndPersScreen(
                        onGoBack = { navController.popBackStack() },
                        onSendRequest = {}
                    )
                }
            }
        }

        composable(Routes.About.route) {
            Scaffold(topBar = topBar()) { padding ->
                Box(Modifier.padding(padding)) {
                    AboutUsScreen()
                }
            }
        }

        composable(Routes.Contact.route) {
            Scaffold(topBar = topBar()) { padding ->
                Box(Modifier.padding(padding)) {
                    ContactScreen()
                }
            }
        }

        composable(Routes.Login.route) {
            Scaffold(topBar = topBar()) { padding ->
                Box(Modifier.padding(padding)) {
                    LoginScreen(
                        onLoginOk = { navController.navigate(Routes.Home.route) },
                        onGoRegister = { navController.navigate(Routes.Register.route) }
                    )
                }
            }
        }

        composable(Routes.Register.route) {
            Scaffold(topBar = topBar()) { padding ->
                Box(Modifier.padding(padding)) {
                    RegistrationScreen(
                        onGoLogin = { navController.navigate(Routes.Login.route) },
                        onRegisterOk = { navController.navigate(Routes.Home.route) }
                    )
                }
            }
        }

        composable(Routes.AdminProducts.route) {
            Scaffold(topBar = topBar()) { padding ->
                Box(Modifier.padding(padding)) {
                    AdminProductScreen()
                }
            }
        }
    }
}
