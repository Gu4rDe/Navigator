package ru.navigator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import ru.navigator.shared.navigation.NavigatorApp
import ru.navigator.shared.navigation.RootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = RootComponent(defaultComponentContext())
        setContent {
            NavigatorApp(root)
        }
    }
}
