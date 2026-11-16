package ru.navigator.desktop

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import ru.navigator.shared.navigation.NavigatorApp
import ru.navigator.shared.navigation.RootComponent

fun main() = application {
    val root = RootComponent(DefaultComponentContext(LifecycleRegistry()))
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            width = 390.dp,
            height = 844.dp,
            position = WindowPosition(Alignment.Center),
        ),
        title = "Навигатор",
    ) {
        NavigatorApp(root)
    }
}
