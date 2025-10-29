package com.example.charmeetchic_grupo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.charmeetchic_grupo2.navigation.AppNavGraph
import com.example.charmeetchic_grupo2.ui.theme.CharmeetChic_Grupo2Theme
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            CharmeetChic_Grupo2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 🔹 Crear un solo CartViewModel para toda la app
                    val cartVM: CartViewModel = viewModel()

                    // 🔹 Crear el controlador de navegación
                    val navController = rememberNavController()

                    // 🔹 Pasar el ViewModel al grafo de navegación
                    AppNavGraph(
                        navController = navController,
                        cartVM = cartVM
                    )
                }
            }
        }
    }
}
