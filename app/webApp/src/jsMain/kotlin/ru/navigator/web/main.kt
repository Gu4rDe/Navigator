package ru.navigator.web

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.browser.document
import ru.navigator.shared.navigation.NavigatorApp
import ru.navigator.shared.navigation.RootComponent

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val root = RootComponent(DefaultComponentContext(LifecycleRegistry()))
    ComposeViewport(document.body!!) {
        NavigatorApp(root)
    }
}
