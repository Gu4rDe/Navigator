plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKmpLibrary)
}

kotlin {
    android {
        namespace = "ru.navigator.core.model"
        compileSdk = 36
        minSdk = 26
    }
    jvm()
    js(IR) {
        nodejs()
    }
    iosArm64()
    iosSimulatorArm64()
}
