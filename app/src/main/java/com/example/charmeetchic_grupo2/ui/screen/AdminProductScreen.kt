package com.example.charmeetchic_grupo2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.charmeetchic_grupo2.model.ProductRequest
import com.example.charmeetchic_grupo2.viewmodel.ProductViewModel
import com.example.charmeetchic_grupo2.domain.validation.*

@Composable
fun AdminProductScreen(
    productVM: ProductViewModel = viewModel()
) {
    val productos by productVM.productList
    val isLoading by productVM.isLoading
    val error by productVM.errorMessage


    // Campos
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var material by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var medidas by remember { mutableStateOf("") }
    var categoriaId by remember { mutableStateOf("") }
    var editingId by remember { mutableStateOf<Long?>(null) }

    // Errores
    var nombreError by remember { mutableStateOf<String?>(null) }
    var precioError by remember { mutableStateOf<String?>(null) }
    var descripcionError by remember { mutableStateOf<String?>(null) }
    var stockError by remember { mutableStateOf<String?>(null) }
    var materialError by remember { mutableStateOf<String?>(null) }
    var pesoError by remember { mutableStateOf<String?>(null) }
    var categoriaError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) { productVM.cargarProductos() }

    // ⚡ SCROLL COMPLETO
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // ---------- TÍTULO ----------
        item {
            Text("Administración de Productos", style = MaterialTheme.typography.titleLarge)
        }

        // ---------- LOADING ----------
        if (isLoading) {
            item {
                CircularProgressIndicator()
            }
            return@LazyColumn
        }

        if (error != null) {
            item {
                Text(text = error!!, color = MaterialTheme.colorScheme.error)
            }
        }

        // ---------- LISTA ----------
        items(productos) { p ->

            var showDeleteDialog by remember { mutableStateOf(false) }
            var idToDelete by remember { mutableStateOf<Long?>(null) }

            Card(
                Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    Modifier.padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(p.nombre, style = MaterialTheme.typography.titleMedium)
                        Text("Precio: \$${p.precio.toInt()}")
                    }

                    Row {
                        TextButton(onClick = {
                            editingId = p.id
                            nombre = p.nombre
                            precio = p.precio.toString()
                            descripcion = p.descripcion
                            stock = p.stock.toString()
                            material = p.material
                            peso = p.peso.toString()
                            medidas = p.medidas
                            categoriaId = p.categoriaId.toString()
                        }) {
                            Text("Editar")
                        }

                        TextButton(
                            onClick = {
                                idToDelete = p.id
                                showDeleteDialog = true
                            }
                        ) {
                            Text("Eliminar", color = MaterialTheme.colorScheme.error)
                        }

                        if (showDeleteDialog && idToDelete != null) {
                            AlertDialog(
                                onDismissRequest = { showDeleteDialog = false },
                                title = { Text("Confirmar eliminación") },
                                text = { Text("¿Estás segura/o de eliminar este producto?") },
                                confirmButton = {
                                    TextButton(
                                        onClick = {
                                            productVM.eliminarProducto(idToDelete!!)
                                            showDeleteDialog = false
                                        }
                                    ) {
                                        Text("Eliminar", color = MaterialTheme.colorScheme.error)
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = { showDeleteDialog = false }) {
                                        Text("Cancelar")
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        // ---------- FORMULARIO ----------
        item {
            Spacer(Modifier.height(16.dp))
            Text(
                if (editingId == null) "Crear Producto" else "Editar Producto",
                style = MaterialTheme.typography.titleMedium
            )
        }

        // Campos del formulario
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                OutlinedTextField(
                    value = nombre,
                    onValueChange = {
                        nombre = it
                        nombreError = validateNotEmpty(it)
                    },
                    label = { Text("Nombre") },
                    isError = nombreError != null
                )
                nombreError?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                OutlinedTextField(
                    value = precio,
                    onValueChange = {
                        precio = it
                        precioError = when {
                            it.isBlank() -> "El precio es obligatorio"
                            it.toDoubleOrNull() == null -> "Debe ser un número válido"
                            it.toDouble() <= 0 -> "Debe ser mayor a 0"
                            else -> null
                        }
                    },
                    label = { Text("Precio") },
                    isError = precioError != null
                )
                precioError?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                OutlinedTextField(
                    value = descripcion,
                    onValueChange = {
                        descripcion = it
                        descripcionError = validateMessageMin5(it)
                    },
                    label = { Text("Descripción") },
                    isError = descripcionError != null
                )
                descripcionError?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                OutlinedTextField(
                    value = stock,
                    onValueChange = {
                        stock = it
                        stockError = if (it.toIntOrNull() == null) "Debe ser un número" else null
                    },
                    label = { Text("Stock") },
                    isError = stockError != null
                )
                stockError?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                OutlinedTextField(
                    value = material,
                    onValueChange = {
                        material = it
                        materialError = validateNotEmpty(it)
                    },
                    label = { Text("Material") },
                    isError = materialError != null
                )
                materialError?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                OutlinedTextField(
                    value = peso,
                    onValueChange = {
                        peso = it
                        pesoError = if (it.toDoubleOrNull() == null) "Debe ser un número" else null
                    },
                    label = { Text("Peso") },
                    isError = pesoError != null
                )
                pesoError?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                OutlinedTextField(
                    value = medidas,
                    onValueChange = { medidas = it },
                    label = { Text("Medidas") }
                )

                OutlinedTextField(
                    value = categoriaId,
                    onValueChange = {
                        categoriaId = it
                        categoriaError = if (it.toLongOrNull() == null) "Debe ser un número válido" else null
                    },
                    label = { Text("ID Categoría") },
                    isError = categoriaError != null
                )
                categoriaError?.let { Text(it, color = MaterialTheme.colorScheme.error) }

                val formHasErrors = listOf(
                    nombreError, precioError, descripcionError, stockError,
                    materialError, pesoError, categoriaError
                ).any { it != null }

                Button(
                    onClick = {
                        val req = ProductRequest(
                            nombre = nombre,
                            descripcion = descripcion,
                            precio = precio.toDouble(),
                            stock = stock.toInt(),
                            material = material,
                            peso = peso.toDouble(),
                            medidas = medidas,
                            categoriaId = categoriaId.toLong(),
                            imagenUrl = null
                        )

                        if (editingId == null) productVM.crearProducto(req)
                        else {
                            productVM.actualizarProducto(editingId!!, req)
                            editingId = null
                        }

                        nombre = ""
                        precio = ""
                        descripcion = ""
                        stock = ""
                        material = ""
                        peso = ""
                        medidas = ""
                        categoriaId = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !formHasErrors
                ) {
                    Text(if (editingId == null) "Crear" else "Guardar Cambios")
                }
            }
        }
    }
}
