rootProject.name = "navigator"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":core:model")
include(":app:shared:ui")
include(":app:shared:navigation")
include(":app:androidApp")
include(":app:desktopApp")
include(":app:webApp")
