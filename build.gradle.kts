import dev.detekt.gradle.Detekt

plugins {
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidKmpLibrary) apply false
    alias(libs.plugins.kotlinxSerialization) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt)
}

// detekt запускается один раз в корне и сканирует весь проект, как ktlintCheck:
// у KMP-модулей с android-kmp-library нет обычных java source set'ов, поэтому
// посчитать источники вручную надёжнее, чем полагаться на авто-определение.
detekt {
    buildUponDefaultConfig = true
    config.setFrom(rootDir.resolve("config/detekt/detekt.yml"))
    source.setFrom(
        fileTree(rootDir) {
            include("**/src/**/*.kt")
            exclude("**/build/**")
        },
    )
}

dependencies {
    detektPlugins(libs.detekt.compose)
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
        sarif.required.set(true)
    }
}
