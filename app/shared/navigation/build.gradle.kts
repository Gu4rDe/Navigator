plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinxSerialization)
}

kotlin {
    android {
        namespace = "ru.navigator.shared.navigation"
        compileSdk = 36
        minSdk = 26
    }
    jvm("desktop")
    js(IR) {
        browser()
        binaries.executable()
    }
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(libs.decompose)
            implementation(libs.decompose.extensions.compose)
            implementation(libs.kotlinx.serialization.json)
            implementation(project(":app:shared:ui"))
        }
    }
}
