plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeCompiler)
}

android {
    namespace = "ru.navigator.android"
    compileSdk = 36

    defaultConfig {
        applicationId = "ru.navigator.android"
        minSdk = 26
        targetSdk = 36
        versionCode = 104
        versionName = "1.4.2"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.decompose)
    implementation(libs.activity.compose)
    implementation("androidx.compose.ui:ui-tooling:1.12.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.12.1")
    implementation(project(":app:shared:navigation"))
    implementation(project(":app:shared:ui"))
    implementation(project(":core:model"))
}
