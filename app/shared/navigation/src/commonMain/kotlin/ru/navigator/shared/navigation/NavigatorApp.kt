package ru.navigator.shared.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.FaultyDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import ru.navigator.shared.ui.components.DesignViewport
import ru.navigator.shared.ui.screens.AboutScreen
import ru.navigator.shared.ui.screens.ArrivalScreen
import ru.navigator.shared.ui.screens.BuildScreen
import ru.navigator.shared.ui.screens.EventScreen
import ru.navigator.shared.ui.screens.GuidanceScreen
import ru.navigator.shared.ui.screens.MapScreen
import ru.navigator.shared.ui.screens.PointScreen
import ru.navigator.shared.ui.screens.PreviewScreen
import ru.navigator.shared.ui.screens.SearchScreen
import ru.navigator.shared.ui.screens.SettingsScreen
import ru.navigator.shared.ui.screens.StatusScreen
import ru.navigator.shared.ui.screens.WelcomeScreen
import ru.navigator.shared.ui.theme.NavigatorColors

/** Корень приложения: дизайн-вьюпорт 390×844 и стек экранов по Prototype flow. */
@OptIn(FaultyDecomposeApi::class)
@Composable
fun NavigatorApp(root: RootComponent) {
    DesignViewport(background = NavigatorColors.SurfaceBase) {
        Children(
            stack = root.stack,
            animation = stackAnimation { _, to, _ ->
                if (to.configuration is ScreenConfig.Arrival) fade() else slide()
            },
        ) { child ->
            when (child.instance.config) {
                ScreenConfig.Welcome -> WelcomeScreen(
                    onAllow = { root.navigateTo(ScreenConfig.Map) },
                    onSkip = { root.navigateTo(ScreenConfig.Map) },
                )

                ScreenConfig.Map -> MapScreen(
                    onSearch = { root.navigateTo(ScreenConfig.Search) },
                    onBuildRoute = { root.navigateTo(ScreenConfig.Build) },
                    onEvent = { root.navigateTo(ScreenConfig.Event) },
                    onStatus = { root.navigateTo(ScreenConfig.Status) },
                    onSettings = { root.navigateTo(ScreenConfig.Settings) },
                    onAbout = { root.navigateTo(ScreenConfig.About) },
                )

                ScreenConfig.Search -> SearchScreen(
                    onBack = root::back,
                    onResult = { root.navigateTo(ScreenConfig.Point) },
                )

                ScreenConfig.Point -> PointScreen(
                    onBack = root::back,
                    onRouteHere = { root.navigateTo(ScreenConfig.Build) },
                    onRouteFrom = { root.navigateTo(ScreenConfig.Build) },
                    onRecalc = { root.navigateTo(ScreenConfig.Preview) },
                )

                ScreenConfig.Build -> BuildScreen(
                    onBack = root::back,
                    onBuild = { root.navigateTo(ScreenConfig.Preview) },
                )

                ScreenConfig.Preview -> PreviewScreen(
                    onBack = root::back,
                    onStart = { root.navigateTo(ScreenConfig.Guidance) },
                    onSimulate = { root.navigateTo(ScreenConfig.Guidance) },
                )

                ScreenConfig.Guidance -> GuidanceScreen(
                    onFinish = { root.navigateTo(ScreenConfig.Arrival) },
                )

                ScreenConfig.Arrival -> ArrivalScreen(
                    onToMap = { root.navigateTo(ScreenConfig.Map) },
                    onReverse = { root.navigateTo(ScreenConfig.Build) },
                )

                ScreenConfig.Event -> EventScreen(onBack = root::back)
                ScreenConfig.Settings -> SettingsScreen(onBack = root::back)
                ScreenConfig.Status -> StatusScreen(onBack = root::back)
                ScreenConfig.About -> AboutScreen(onBack = root::back)
            }
        }
    }
}
