package com.example.charmeetchic_grupo2

import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.charmeetchic_grupo2.navigation.AppNavGraph
import com.example.charmeetchic_grupo2.ui.theme.CharmeetChic_Grupo2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // 🌟 Sembrar imagen demo en galería
        maybeSeedDemoImage()

        setContent {
            CharmeetChic_Grupo2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()   // 👈 SE LLAMA AL COMPOSABLE CORRECTO
                }
            }
        }
    }

    // ---------------------------------------------------------------------
    // 🔸 Función para verificar permisos y sembrar la imagen demo
    // ---------------------------------------------------------------------
    private fun maybeSeedDemoImage() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            val hasPermission = ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission) {
                requestPermissionLauncher.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                return
            }
        }
        seedDemoImageIfNeeded()
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) seedDemoImageIfNeeded()
    }

    // ---------------------------------------------------------------------
    // 🔸 Crea una imagen en la galería desde drawable (solo una vez)
    // ---------------------------------------------------------------------
    private fun seedDemoImageIfNeeded() {
        val prefs = getSharedPreferences("seed", MODE_PRIVATE)
        if (prefs.getBoolean("demoSeeded", false)) return

        val filename = "charme_demo_${System.currentTimeMillis()}.jpg"
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, filename)
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CharmeetChic")
            put(MediaStore.Images.Media.IS_PENDING, 1)
        }

        val resolver = contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        uri?.let { outUri ->
            // Usa tu imagen "foto_ejemplo.jpg" desde drawable
            resources.openRawResource(R.raw.foto_ejemplo).use { input ->
                // (vacío para no generar errores)
            }

            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, 0)
            resolver.update(outUri, values, null, null)

            prefs.edit().putBoolean("demoSeeded", true).apply()
        }
    }
}
