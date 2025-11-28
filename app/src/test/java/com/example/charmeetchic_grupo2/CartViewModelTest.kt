package com.example.charmeetchic_grupo2

import com.example.charmeetchic_grupo2.model.*
import com.example.charmeetchic_grupo2.repository.CartRepository
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CartViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repo: CartRepository
    private lateinit var vm: CartViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repo = mockk(relaxed = true)
        vm = CartViewModel(repo, testDispatcher)
    }

    @Test
    fun cargarCarrito_actualizaState() = runTest {
        // 🔹 Producto falso
        val fakeProductDTO = ProductDTO(
            id = 1,
            nombre = "Anillo Oro",
            descripcion = "Anillo elegante",
            precio = 19990.0,
            stock = 10,
            material = "Oro",
            peso = 1.2,
            medidas = "2cm",
            categoriaId = 1,
            imagenUrl = null
        )

        // 🔹 Carrito falso
        val fake = CartResponse(
            compraId = 1L,
            usuarioId = 1L,
            estado = "PENDIENTE",
            total = 19990.0,
            items = listOf(
                CartItemDTO(
                    id = 1,
                    productoId = 1,
                    cantidad = 1,
                    producto = fakeProductDTO
                )
            )
        )

        coEvery { repo.obtenerCarrito(1L) } returns fake

        vm.cargarCarrito()
        advanceUntilIdle()   // Esperar corrutinas

        val state = vm.state.value

        assertEquals(1, state.items.size)
        assertEquals(19990.0, state.total, 0.0)
        assertEquals("Anillo Oro", state.items[0].producto.nombre)
    }
}
