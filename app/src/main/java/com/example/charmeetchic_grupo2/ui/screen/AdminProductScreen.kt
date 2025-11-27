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

@Composable
fun AdminProductScreen(
    productVM: ProductViewModel = viewModel()
) {
    val productos = productVM.productList
    val isLoading = productVM.isLoading
    val error = productVM.errorMessage

    // Campos del formulario (crear / editar)
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }
    var material by remember { mutableStateOf("") }
    var peso by remember { mutableStateOf("") }
    var medidas by remember { mutableStateOf("") }
    var categoriaId by remember { mutableStateOf("") }
    var editingId by remember { mutableStateOf<Long?>(null) }

    // Cargar productos al entrar
    LaunchedEffect(Unit) { productVM.cargarProductos() }

    Column(Modifier.padding(16.dp)) {

        Text("Administración de Productos", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        // 🔄 Loading
        if (isLoading) {
            CircularProgressIndicator()
            return@Column
        }

        // ❌ Error
        if (error != null) {
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }

        // 📝 LISTA DE PRODUCTOS
        LazyColumn(modifier = Modifier.fillMaxHeight(0.4f)) {
            items(productos) { p ->
                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(p.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Precio: \$${p.precio.toInt()}")
                        }

                        Row {
                            // ✏ EDITAR
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

                            // ❌ ELIMINAR
                            TextButton(onClick = {
                                productVM.eliminarProducto(p.id)
                            }) {
                                Text("Eliminar", color = MaterialTheme.colorScheme.error)
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Text(
            if (editingId == null) "Crear Producto" else "Editar Producto",
            style = MaterialTheme.typography.titleMedium
        )

        // ⭐ FORMULARIO (COMÚN PARA CREAR / EDITAR)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
            OutlinedTextField(value = precio, onValueChange = { precio = it }, label = { Text("Precio") })
            OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") })
            OutlinedTextField(value = stock, onValueChange = { stock = it }, label = { Text("Stock") })
            OutlinedTextField(value = material, onValueChange = { material = it }, label = { Text("Material") })
            OutlinedTextField(value = peso, onValueChange = { peso = it }, label = { Text("Peso") })
            OutlinedTextField(value = medidas, onValueChange = { medidas = it }, label = { Text("Medidas") })
            OutlinedTextField(value = categoriaId, onValueChange = { categoriaId = it }, label = { Text("ID Categoría") })

            // BOTÓN PRINCIPAL (CREAR o EDITAR)
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

                    if (editingId == null) {
                        productVM.crearProducto(req)
                    } else {
                        productVM.actualizarProducto(editingId!!, req)
                        editingId = null
                    }

                    // limpiar form
                    nombre = ""
                    precio = ""
                    descripcion = ""
                    stock = ""
                    material = ""
                    peso = ""
                    medidas = ""
                    categoriaId = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (editingId == null) "Crear" else "Guardar Cambios")
            }
        }
    }
}
