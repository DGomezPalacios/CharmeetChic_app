// ============================================================
// PLUGINS
// ============================================================
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.25"
}

// ============================================================
// ANDROID CONFIG
// ============================================================
android {
    namespace = "com.example.charmeetchic_grupo2"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.charmeetchic_grupo2"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // ========================================================
    // SIGNING CONFIG (para APK firmada)
    // ========================================================
    signingConfigs {
        create("release") {
            storeFile = file("C:/Users/Daniela/Documents/DUOC/App Moviles/keystores/charmeetchic.jks")
            storePassword = "charme2025"
            keyAlias = "charme_key"
            keyPassword = "charme2025"
        }
    }

    // ========================================================
    // BUILD TYPES
    // ========================================================
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }

    // ========================================================
    // COMPATIBILIDAD KOTLIN / JAVA
    // ========================================================
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    // ========================================================
    // COMPOSE ACTIVADO
    // ========================================================
    buildFeatures {
        compose = true
    }
}

// ============================================================
// DEPENDENCIAS UNIFICADAS
// ============================================================
dependencies {

    // ------------------------------------------------------------
    // ⬛ CORE + COMPOSE
    // ------------------------------------------------------------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // ------------------------------------------------------------
    // ⬛ ANDROIDX / MATERIAL
    // ------------------------------------------------------------
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.compose.runtime:runtime-saveable:1.7.3")
    implementation("androidx.compose.material:material-icons-extended:1.7.3")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.core:core-splashscreen:1.0.1")

    // ------------------------------------------------------------
    // ⬛ NAVEGACIÓN
    // ------------------------------------------------------------
    implementation("androidx.navigation:navigation-compose:2.8.3")

    // ------------------------------------------------------------
    // ⬛ RETROFIT + GSON + OKHTTP
    // ------------------------------------------------------------
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // ------------------------------------------------------------
    // ⬛ CORRUTINAS + SERIALIZACIÓN
    // ------------------------------------------------------------
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")

    // ------------------------------------------------------------
    // ⬛ DATASTORE
    // ------------------------------------------------------------
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    // ------------------------------------------------------------
    // ⬛ COIL IMAGENES
    // ------------------------------------------------------------
    implementation("io.coil-kt:coil-compose:2.7.0")

    // ------------------------------------------------------------
    // ⬛ TEST UNITARIOS (JUnit4, JUnit5, MockK, Kotest)
    // ------------------------------------------------------------
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.mockk:mockk:1.13.10")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // ------------------------------------------------------------
    // ⬛ ANDROID TEST
    // ------------------------------------------------------------
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // ------------------------------------------------------------
    // ⬛ DEBUG
    // ------------------------------------------------------------
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.2")
}

// ============================================================
// CONFIGURACIÓN JUNIT5
// ============================================================
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
