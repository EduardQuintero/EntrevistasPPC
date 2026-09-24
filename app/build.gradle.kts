plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.entrevistasppc"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.entrevistasppc"
        minSdk = 33
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))

    // Compose core
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // Foundation + runtime
    implementation("androidx.compose.foundation:foundation:1.6.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.0")

    // Necesario para Modifier y dp
    implementation("androidx.compose.ui:ui-unit:1.6.0")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.0")

    // Room
    implementation("androidx.room:room-compiler:2.6.1")

    implementation("androidx.room:room-runtime:2.6.1") {
    implementation("androidx.room:room-ktx:2.6.1")
    }
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Core
    implementation(libs.androidx.core.ktx)

    // Tests
    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)

    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
}

// Forzar versión correcta de anotaciones para evitar duplicados
configurations.all {
    exclude(group = "com.intellij", module = "annotations")
}