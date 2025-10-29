package com.example.charmeetchic_grupo2.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    onGoHome: () -> Unit,
    onGoCatalog: () -> Unit,
    onGoCart: () -> Unit,
    onGoRepare: () -> Unit,
    onGoAbout: () -> Unit,
    onGoContact: () -> Unit,
    onGoLogin: () -> Unit,
    onGoRegister: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "✨ Charme et Chic ✨",
                modifier = Modifier.clickable { onGoHome() },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground // 🔹 Texto negro del título
            )
        },
        navigationIcon = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.Menu, contentDescription = "Menú principal")
            }

            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Inicio") },
                    leadingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                    onClick = { showMenu = false; onGoHome() }
                )

                DropdownMenuItem(
                    text = { Text("Catálogo") },
                    leadingIcon = { Icon(Icons.Default.Storefront, contentDescription = null) },
                    onClick = { showMenu = false; onGoCatalog() }
                )

                DropdownMenuItem(
                    text = { Text("Reparar y Personalizar") },
                    leadingIcon = { Icon(Icons.Default.Build, contentDescription = null) },
                    onClick = { showMenu = false; onGoRepare() }
                )

                DropdownMenuItem(
                    text = { Text("Sobre nosotros") },
                    leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) },
                    onClick = { showMenu = false; onGoAbout() }
                )

                DropdownMenuItem(
                    text = { Text("Contacto") },
                    leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                    onClick = { showMenu = false; onGoContact() }
                )

                // 🧭 Separador moderno
                HorizontalDivider()

                DropdownMenuItem(
                    text = { Text("Iniciar sesión") },
                    leadingIcon = { Icon(Icons.AutoMirrored.Filled.Login, contentDescription = null) },
                    onClick = { showMenu = false; onGoLogin() }
                )

                DropdownMenuItem(
                    text = { Text("Registrarse") },
                    leadingIcon = { Icon(Icons.Default.PersonAdd, contentDescription = null) },
                    onClick = { showMenu = false; onGoRegister() }
                )
            }
        },
        actions = {
            IconButton(onClick = onGoCart) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Carrito de compras"
                )
            }
        }
    )
}
