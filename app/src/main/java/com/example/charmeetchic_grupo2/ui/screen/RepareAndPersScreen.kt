package com.example.charmeetchic_grupo2.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.charmeetchic_grupo2.ui.theme.CharmeetChicUI
import com.example.charmeetchic_grupo2.ui.theme.Dorado
import com.example.charmeetchic_grupo2.ui.theme.TextoOscuro
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.charmeetchic_grupo2.domain.validation.*


private val UriSaver: Saver<Uri?, String> = Saver(
    save = { it?.toString() ?: "" },
    restore = { s -> if (s.isEmpty()) null else Uri.parse(s) }
)

@Composable
fun RepareAndPersScreen(
    onGoBack: () -> Unit = {},
    onSendRequest: () -> Unit = {}
) {
    var selected by rememberSaveable(stateSaver = UriSaver) { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var serviceType by remember { mutableStateOf("Reparación") }
    var showSuccess by remember { mutableStateOf(false) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var descriptionError by remember { mutableStateOf<String?>(null) }


    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri -> selected = uri }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Reparación y Personalización",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Text(
                "Completa tus datos, selecciona el tipo de servicio y adjunta una imagen de tu joya.",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline)

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    nameError = validateNameLettersOnly(name)
                },
                label = { Text("Nombre completo") },
                isError = nameError != null,
                supportingText = {
                    nameError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = CharmeetChicUI.textFieldColors
            )


            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                    phoneError = validatePhoneDigitsOnly(phone)
                },
                label = { Text("Teléfono") },
                isError = phoneError != null,
                supportingText = {
                    phoneError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = CharmeetChicUI.textFieldColors
            )


            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = validateEmail(email)
                },
                label = { Text("Correo electrónico") },
                isError = emailError != null,
                supportingText = {
                    emailError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = CharmeetChicUI.textFieldColors
            )

            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                    descriptionError = if (it.isBlank()) "Campo obligatorio" else null
                },
                label = { Text("Descripción del trabajo") },
                isError = descriptionError != null,
                supportingText = {
                    descriptionError?.let { Text(it, color = MaterialTheme.colorScheme.error) }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = CharmeetChicUI.textFieldColors
            )





            // 🔸 Tipo de servicio con estilo intuitivo
            Text("Tipo de servicio", style = MaterialTheme.typography.bodyMedium)

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val inactiveButtonColor = Color(0xFFF5EDE3) // beige suave
                val borderColor = Dorado.copy(alpha = 0.6f)

                // Botón Reparación
                Button(
                    onClick = { serviceType = "Reparación" },
                    colors = if (serviceType == "Reparación")
                        CharmeetChicUI.buttonColors
                    else ButtonDefaults.buttonColors(
                        containerColor = inactiveButtonColor,
                        contentColor = TextoOscuro
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = if (serviceType == "Reparación") 0.dp else 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text("Reparación")
                }

                // Botón Personalización
                Button(
                    onClick = { serviceType = "Personalización" },
                    colors = if (serviceType == "Personalización")
                        CharmeetChicUI.buttonColors
                    else ButtonDefaults.buttonColors(
                        containerColor = inactiveButtonColor,
                        contentColor = TextoOscuro
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .border(
                            width = if (serviceType == "Personalización") 0.dp else 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text("Personalización")
                }
            }


            Button(
                onClick = {
                    picker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                },
                colors = CharmeetChicUI.buttonColors,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(30.dp)
            ) {
                Text("📷 Adjuntar imagen")
            }

            AnimatedVisibility(
                visible = selected != null,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Card(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(top = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    AsyncImage(
                        model = selected,
                        contentDescription = "Imagen seleccionada",
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(Modifier.weight(1f, fill = true))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()   // evita quedar debajo de la barra
                    .padding(bottom = 16.dp),  // respirito final
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onGoBack,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(30.dp)
                ) { Text("Volver") }

                Button(
                    onClick = {
                        showSuccess = true
                        onSendRequest()
                        scope.launch {
                            snackbarHostState.showSnackbar("✨ Solicitud enviada correctamente")
                            delay(2000)
                            showSuccess = false
                        }
                    },
                    enabled = selected != null &&
                            nameError == null &&
                            phoneError == null &&
                            emailError == null &&
                            descriptionError == null &&
                            name.isNotBlank(),
                    colors = CharmeetChicUI.buttonColors,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(30.dp)
                ) { Text("Enviar solicitud") }
            }

            AnimatedVisibility(
                visible = showSuccess,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(
                    "Tu solicitud fue enviada correctamente 💖",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(Modifier.height(60.dp))
        }
    }
}
