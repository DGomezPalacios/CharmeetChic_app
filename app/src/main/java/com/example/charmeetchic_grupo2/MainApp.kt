package com.example.charmeetchic_grupo2

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.charmeetchic_grupo2.navigation.AppNavGraph
import com.example.charmeetchic_grupo2.viewmodel.AuthViewModel
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel

@Composable
fun MainApp() {
    val navController = rememberNavController()

    val cartVM: CartViewModel = viewModel()
    val authVM: AuthViewModel = viewModel()

    AppNavGraph(
        navController = navController,
        cartVM = cartVM,
        authVM = authVM
    )
}
