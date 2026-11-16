package ru.navigator.shared.navigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

/** Экраны мобильного макета (mobile.uipage, screen.*). */
@Serializable
sealed interface ScreenConfig {
    @Serializable data object Welcome : ScreenConfig
    @Serializable data object Map : ScreenConfig
    @Serializable data object Search : ScreenConfig
    @Serializable data object Point : ScreenConfig
    @Serializable data object Build : ScreenConfig
    @Serializable data object Preview : ScreenConfig
    @Serializable data object Guidance : ScreenConfig
    @Serializable data object Arrival : ScreenConfig
    @Serializable data object Event : ScreenConfig
    @Serializable data object Settings : ScreenConfig
    @Serializable data object Status : ScreenConfig
    @Serializable data object About : ScreenConfig
}

class RootComponent(context: ComponentContext) : ComponentContext by context {

    private val navigation = StackNavigation<ScreenConfig>()

    val stack: Value<ChildStack<ScreenConfig, ScreenComponent>> = childStack(
        source = navigation,
        serializer = ScreenConfig.serializer(),
        initialConfiguration = ScreenConfig.Welcome,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(config: ScreenConfig, componentContext: ComponentContext): ScreenComponent =
        ScreenComponent(config, componentContext)

    fun navigateTo(config: ScreenConfig) {
        navigation.push(config)
    }

    fun back() {
        navigation.pop()
    }
}

class ScreenComponent(
    val config: ScreenConfig,
    context: ComponentContext,
) : ComponentContext by context
