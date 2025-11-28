package com.example.charmeetchic_grupo2


import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import com.example.charmeetchic_grupo2.ui.screen.CatalogScreen
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel
import org.junit.Rule
import org.junit.Test

class CatalogScreenTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun catalogScreen_muestraTituloYProductos() {
        val cartVM = CartViewModel()

        rule.setContent {
            CatalogScreen(cartVM = cartVM)
        }

        // 1) Verifica título
        rule.onNodeWithText("Catálogo").assertExists()

        // 2) Verifica que existan productos
        val productos = rule.onAllNodesWithText("Precio")
        assert(productos.fetchSemanticsNodes().isNotEmpty())
    }
}