import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvm()

    sourceSets {
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(libs.decompose)
            implementation(project(":app:shared:navigation"))
            implementation(project(":app:shared:ui"))
            implementation(project(":core:model"))
        }
    }
}

compose.desktop {
    application {
        mainClass = "ru.navigator.desktop.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Navigator"
            packageVersion = "1.4.2"
        }
    }
}
