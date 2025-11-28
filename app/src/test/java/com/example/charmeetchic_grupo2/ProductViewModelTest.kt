package com.example.charmeetchic_grupo2

import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.repository.ProductRepository
import com.example.charmeetchic_grupo2.viewmodel.ProductViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repo: ProductRepository
    private lateinit var vm: ProductViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        repo = mockk(relaxed = true)
        vm = ProductViewModel(repo, testDispatcher)
    }

    @Test
    fun cargarProductos_actualizaListaEnState() = runTest {
        val fakeProducts = listOf(
            Product(
                id = 1,
                nombre = "Anillo Oro",
                descripcion = "Anillo delicado 18k",
                precio = 25990.0,
                stock = 10,
                material = "Oro",
                peso = 5.0,
                medidas = "2x2",
                categoriaId = 1
            ),
            Product(
                id = 2,
                nombre = "Collar Plata",
                descripcion = "Plata 925",
                precio = 19990.0,
                stock = 8,
                material = "Plata",
                peso = 4.0,
                medidas = "10cm",
                categoriaId = 1
            )
        )

        coEvery { repo.getAllProducts() } returns fakeProducts

        vm.cargarProductos()
        advanceUntilIdle()

        assertEquals(fakeProducts, vm.productList.value)
    }
}