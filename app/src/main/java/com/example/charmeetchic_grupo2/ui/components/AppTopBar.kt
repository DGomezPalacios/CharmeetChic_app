package com.example.charmeetchic_grupo2.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier

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
    onGoRegister: () -> Unit,
) {
    var showMenu by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "✨ Charme et Chic ✨",
                modifier = Modifier.clickable { onGoHome() },
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(Icons.Default.Menu, contentDescription = "Menú")
            }
            DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(text = { Text("Inicio") }, leadingIcon = {
                    Icon(Icons.Default.Home, null)
                }, onClick = { showMenu = false; onGoHome() })

                DropdownMenuItem(text = { Text("Catálogo") }, leadingIcon = {
                    Icon(Icons.Default.Info, null)
                }, onClick = { showMenu = false; onGoCatalog() })

                DropdownMenuItem(
                    text = { Text("Reparar y Personalizar") },
                    leadingIcon = { Icon(Icons.Default.Build, null) },
                    onClick = { showMenu = false; onGoRepare() }
                )

                DropdownMenuItem(text = { Text("Sobre nosotros") }, leadingIcon = {
                    Icon(Icons.Default.Info, null)
                }, onClick = { showMenu = false; onGoAbout() })

                DropdownMenuItem(text = { Text("Contacto") }, leadingIcon = {
                    Icon(Icons.Default.Email, null)
                }, onClick = { showMenu = false; onGoContact() })

                Divider()

                DropdownMenuItem(text = { Text("Iniciar sesión") }, leadingIcon = {
                    Icon(Icons.Default.Login, null)
                }, onClick = { showMenu = false; onGoLogin() })

                DropdownMenuItem(text = { Text("Registrarse") }, leadingIcon = {
                    Icon(Icons.Default.PersonAdd, null)
                }, onClick = { showMenu = false; onGoRegister() })
            }
        },
        actions = {
            IconButton(onClick = onGoCart) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
            }
        }
    )
}
