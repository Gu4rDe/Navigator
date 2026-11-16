plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    js {
        browser {
            binaries.executable()
        }
    }

    sourceSets {
        jsMain.dependencies {
            implementation(libs.compose.ui)
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.decompose)
            implementation(project(":app:shared:navigation"))
            implementation(project(":app:shared:ui"))
            implementation(project(":core:model"))
        }
    }
}
