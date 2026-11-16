package ru.navigator.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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

@Preview(name = "1 Welcome", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewWelcome() = WelcomeScreen(onAllow = {}, onSkip = {})

@Preview(name = "2 Map", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewMap() = MapScreen({}, {}, {}, {}, {}, {})

@Preview(name = "3 Search", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewSearch() = SearchScreen(onBack = {}, onResult = {})

@Preview(name = "4 Point", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewPoint() = PointScreen(onBack = {}, onRouteHere = {}, onRouteFrom = {}, onRecalc = {})

@Preview(name = "5 Build", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewBuild() = BuildScreen(onBack = {}, onBuild = {})

@Preview(name = "6 PreviewRoutes", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewRoutes() = PreviewScreen(onBack = {}, onStart = {}, onSimulate = {})

@Preview(name = "7 Guidance", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewGuidance() = GuidanceScreen(onFinish = {})

@Preview(name = "8 Arrival", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewArrival() = ArrivalScreen(onToMap = {}, onReverse = {})

@Preview(name = "9 Event", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewEvent() = EventScreen(onBack = {})

@Preview(name = "10 Settings", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewSettings() = SettingsScreen(onBack = {})

@Preview(name = "11 Status", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewStatus() = StatusScreen(onBack = {})

@Preview(name = "12 About", showBackground = true, widthDp = 390, heightDp = 844)
@Composable
private fun PreviewAbout() = AboutScreen(onBack = {})
