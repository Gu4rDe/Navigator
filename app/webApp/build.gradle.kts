plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    js(IR) {
        browser {
            binaries.executable()
        }
    }

    sourceSets {
        jsMain.dependencies {
            implementation(compose.ui)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(libs.decompose)
            implementation(project(":app:shared:navigation"))
            implementation(project(":app:shared:ui"))
            implementation(project(":core:model"))
        }
    }
}
