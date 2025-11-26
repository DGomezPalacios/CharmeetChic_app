package com.example.charmeetchic_grupo2

import com.example.charmeetchic_grupo2.model.Product
import com.example.charmeetchic_grupo2.viewmodel.CartViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CartViewModelTest {

    @Test
    fun agregarProductoAumentaCantidadYTotal() {
        runTest {
            val vm = CartViewModel()

            val product = Product(
                id = 1L,
                nombre = "Collar Dorado",
                precio = 10000.0,
                categoria = "Collares"
            )

            vm.add(product)

            val state = vm.state.value

            assertEquals(1, state.items.size)
            assertEquals(1, state.items[0].qty)
            assertEquals(10000.0, state.total, 0.0)
        }
    }
}
