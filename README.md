# Charme et Chic — App Móvil (Compose + MVVM)

**Integrantes:** Daniela Gómez Palacios, Berta Soto Jerez  
**Asignatura:** DSY1105
**Docente:** Sergio Fuentes Perez

## 🎯 Objetivo
App móvil para vitrinear joyas, agregar al carrito y contactar a la tienda. Construida con **Jetpack Compose + Material 3**, navegación con `NavHost`, arquitectura **MVVM**, almacenamiento local con **DataStore** y uso de **recursos nativos** (llamadas, maps, image picker/galería).

## ✨ Funcionalidades
- Home, Catálogo, Carrito, Contacto, Login/Registro, About, Reparación y Personalización.
- Validaciones visuales (errores/ok) en **Login, Registro, Contacto y Reparación**.
- Barra superior (**TopAppBar**) con navegación a todas las pantallas.
- Recursos nativos:
    - **Contacto:** llamar / abrir Maps.
    - **Reparar/Personalizar:** **PhotoPicker** / galería.
- **Persistencia local (DataStore):** sesión y carrito.

## 🗺️ Arquitectura
- **MVVM + StateFlow**: `ViewModel` expone `UiState` inmutable; los Composables solo observan estado.
- **Dominio/Validaciones**: `domain/validation/Validators.kt`
- **Data**: `data/SessionDataStore.kt`, `data/CartDataStore.kt`
- **UI**: `ui/screen/*`, `ui/components/*`
- **Navegación**: `navigation/NavGraph.kt`, `navigation/Routes.kt`

## ▶️ Cómo ejecutar
1. Android Studio Giraffe+ • SDK 35 (o 34)
2. `Sync Project`
3. Ejecutar en **emulador físico/lógico** (permisos de teléfono/mapas habilitados).

## 📊 Herramientas colaborativas
- **Repositorio GitHub:**  
  [https://github.com/DGomezPalacios/CharmeetChic_app](https://github.com/DGomezPalacios/CharmeetChic_app)
- **Tablero Jira (Scrum Board):**  
  [https://duocuc-team-znu3stsr.atlassian.net/jira/software/projects/SCRUM/boards/1](https://duocuc-team-znu3stsr.atlassian.net/jira/software/projects/SCRUM/boards/1)
- **Metodología:** Scrum (2 integrantes)
    - Historias de usuario y tareas distribuidas equitativamente.
    - Sprint activo con tareas en progreso, QA y completadas.
    - Evidencia visual en Jira y commits asociados a cada ticket