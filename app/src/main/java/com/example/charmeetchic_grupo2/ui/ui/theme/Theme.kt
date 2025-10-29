package com.example.charmeetchic_grupo2.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

// 🌞 Tema claro
private val LightColors = lightColorScheme(
    primary = Dorado,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    secondary = Lavanda,
    onSecondary = androidx.compose.ui.graphics.Color.White,
    background = BlancoSuave,
    onBackground = TextoOscuro,
    surface = androidx.compose.ui.graphics.Color.White,
    onSurface = TextoOscuro,
    outline = GrisClaro
)

// 🌙 Tema oscuro
private val DarkColors = darkColorScheme(
    primary = DoradoOscuro,
    onPrimary = androidx.compose.ui.graphics.Color.Black,
    secondary = Lavanda,
    onSecondary = androidx.compose.ui.graphics.Color.Black,
    background = androidx.compose.ui.graphics.Color(0xFF121212),
    onBackground = androidx.compose.ui.graphics.Color.White,
    surface = androidx.compose.ui.graphics.Color(0xFF1E1E1E),
    onSurface = androidx.compose.ui.graphics.Color.White,
    outline = androidx.compose.ui.graphics.Color(0xFF333333)
)

// 💎 Tema global Charme et Chic
@Composable
fun CharmeetChic_Grupo2Theme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        shapes = Shapes(
            small = RoundedCornerShape(6.dp),
            medium = RoundedCornerShape(10.dp),
            large = RoundedCornerShape(16.dp)
        ),
        content = content
    )
}

// ✨ Estilos reutilizables para botones y campos
object CharmeetChicUI {

    val buttonColors: ButtonColors
        @Composable
        get() = ButtonDefaults.buttonColors(
            containerColor = Dorado,
            contentColor = androidx.compose.ui.graphics.Color.White,
            disabledContainerColor = Dorado.copy(alpha = 0.4f)
        )

    val textFieldColors: TextFieldColors
        @Composable
        get() = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Dorado,
            unfocusedBorderColor = GrisClaro,
            cursorColor = Dorado
        )
}
