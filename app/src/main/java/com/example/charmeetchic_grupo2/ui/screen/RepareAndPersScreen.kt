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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.charmeetchic_grupo2.domain.validation.validateEmail
import com.example.charmeetchic_grupo2.domain.validation.validateNameLettersOnly
import com.example.charmeetchic_grupo2.domain.validation.validatePhoneDigitsOnly
import com.example.charmeetchic_grupo2.ui.theme.CharmeetChicUI
import com.example.charmeetchic_grupo2.ui.theme.Dorado
import com.example.charmeetchic_grupo2.ui.theme.TextoOscuro
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.text.KeyboardOptions

// Saver para guardar/restaurar un Uri? como String
private val UriSaver: Saver<Uri?, String> = Saver(
    save = { it?.toString() ?: "" },
    restore = { s -> if (s.isEmpty()) null else Uri.parse(s) }
)

@Composable
fun RepareAndPersScreen(
    onGoBack: () -> Unit = {},
    onSendRequest: () -> Unit = {}
) {
    // --------- Estados de UI ----------
    var selected by rememberSaveable(stateSaver = UriSaver) { mutableStateOf<Uri?>(null) }
    var name by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var serviceType by rememberSaveable { mutableStateOf("Reparación") }

    // Errores de validación (null = OK)
    val nameError = validateNameLettersOnly(name)
    val phoneDigitsError = validatePhoneDigitsOnly(phone)
    val phoneLenError = if (phone.length < 9) "Debe tener al menos 9 dígitos" else null
    val phoneError = phoneDigitsError ?: phoneLenError
    val emailError = validateEmail(email)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val picker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri -> selected = uri }

    // UI
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
                .padding(horizontal = 24.dp, vertical = 20.dp),
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
                onValueChange = { name = it },
                label = { Text("Nombre completo") },
                isError = nameError != null,
                supportingText = { if (nameError != null) Text(nameError) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = CharmeetChicUI.textFieldColors
            )

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Teléfono") },
                isError = phoneError != null,
                supportingText = { if (phoneError != null) Text(phoneError!!) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = CharmeetChicUI.textFieldColors
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                isError = emailError != null,
                supportingText = { if (emailError != null) Text(emailError!!) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = CharmeetChicUI.textFieldColors
            )

            Text("Tipo de servicio", style = MaterialTheme.typography.bodyMedium)

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                val inactiveButtonColor = MaterialTheme.colorScheme.surfaceVariant
                val borderColor = Dorado.copy(alpha = 0.6f)

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
                ) { Text("Reparación") }

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
                ) { Text("Personalización") }
            }

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Descripción del trabajo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = CharmeetChicUI.textFieldColors
            )

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
                Text("📷 Adjuntar imagen (opcional)")
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
                        contentDescription = "Imagen seleccionada"
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onGoBack,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(30.dp)
                ) { Text("Volver") }

                val canSend = nameError == null && phoneError == null && emailError == null

                Button(
                    onClick = {
                        if (canSend) {
                            onSendRequest()
                            scope.launch {
                                snackbarHostState.showSnackbar("✨ Solicitud enviada correctamente")
                            }
                            // Mantenerse en la pantalla, opcionalmente limpiar campos:
                            // name = ""; phone = ""; email = ""; description = ""; selected = null
                        }
                    },
                    enabled = canSend,
                    colors = CharmeetChicUI.buttonColors,
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(30.dp)
                ) { Text("Enviar solicitud") }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}
