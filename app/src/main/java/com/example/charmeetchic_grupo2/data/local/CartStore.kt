package com.example.charmeetchic_grupo2.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "cart_prefs")

class CartStore(private val context: Context) {

    companion object {
        private val CART_KEY = stringPreferencesKey("cart_summary")
    }

    // Guarda el resumen del carrito
    suspend fun saveCartInfo(info: String) {
        context.dataStore.edit { prefs ->
            prefs[CART_KEY] = info
        }
    }

    // Obtiene el resumen guardado
    val cartInfo: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[CART_KEY] ?: ""
    }

    // Limpia el valor guardado
    suspend fun clearCart() {
        context.dataStore.edit { it.remove(CART_KEY) }
    }
}
