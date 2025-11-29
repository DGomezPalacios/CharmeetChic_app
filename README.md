# Charme et Chic — App Móvil

**Integrantes:** Daniela Gómez Palacios, Berta Soto Jerez  
**Asignatura:** DSY1105
**Docente:** Sergio Fuentes Perez

## 🎯 Sobre la app

Proyecto desarrollado para la asignatura **Desarrollo de Aplicaciones Móviles**.  
La app permite gestionar productos, visualizar información, agregar al carrito, realizar flujos CRUD.
Está construida en **Android Studio + Jetpack Compose** e integrada con microservicios en Spring Boot.

## 🔧 Funcionalidades Principales
- Interfaz visual completa creada con Jetpack Compose
- CRUD funcional conectado al microservicio:
  - Crear, listar, actualizar y eliminar productos
- Carrito de compras con ViewModel
- Consumo de API externa integrado al flujo visual
- Validaciones visuales y manejo de errores
- Pruebas unitarias implementadas (ProductoViewModel y CartViewModel)
- Actualización en vivo tras operaciones CRUD
- APK firmado listo para instalación

## 🔗 Endpoints Utilizados

### 🟦 Microservicio Productos (Spring Boot)
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET    | `/productos` | Listar productos |
| GET    | `/productos/{id}` | Obtener producto |
| POST   | `/productos` | Crear producto |
| PUT    | `/productos/{id}` | Actualizar producto |
| DELETE | `/productos/{id}` | Eliminar producto |

## ▶ Pasos para Ejecutar

### 📌 **Backend (Spring Boot)**
1. Clonar el repositorio del microservicio (Sumativa2).
2. Ejecutar el proyecto en Spring Boot con MySQL activo.
3. Verificar disponibilidad en puerto respectivo de Compras y Productos

### 📌 **Aplicación Móvil**
1. Abrir el proyecto en Android Studio.
2. Revisar la URL base en Retrofit (`http://10.0.2.2:(el que corresponda)/`).
3. Ejecutar la app en emulador o dispositivo físico.
4. Permitir tráfico HTTP para desarrollo (cleartextTraffic).

## 📊 Herramientas colaborativas
- **Repositorio GitHub:**  
  [https://github.com/DGomezPalacios/CharmeetChic_app](https://github.com/DGomezPalacios/CharmeetChic_app)
- **Tablero Jira (Scrum Board):**  
  [https://duocuc-team-znu3stsr.atlassian.net/jira/software/projects/SCRUM/boards/1](https://duocuc-team-znu3stsr.atlassian.net/jira/software/projects/SCRUM/boards/1)
- **Metodología:** Scrum (2 integrantes)
  - Historias de usuario y tareas distribuidas equitativamente.
  - Sprint activo con tareas en progreso, QA y completadas.
  - Evidencia visual en Jira y commits asociados a cada ticket