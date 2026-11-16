package ru.navigator.shared.ui.theme

import androidx.compose.ui.graphics.Color

/** Цветовые токены из `.kimi-design/.../index.kui` (KUI tokens). */
object NavigatorColors {
    // brand
    val BrandPrimary = Color(0xFF1677FF)
    val BrandHover = Color(0xFF4096FF)
    val BrandActive = Color(0xFF0958D9)
    val BrandBg = Color(0xFFE6F4FF)

    // surface
    val SurfaceBase = Color(0xFFF5F5F5)
    val SurfaceCard = Color(0xFFFFFFFF)
    val SurfaceScrim = Color(0x73000000)

    // map
    val MapCanvas = Color(0xFF1B2735)
    val MapBlock = Color(0xFF25344A)
    val MapRoad = Color(0xFF3A4E66)
    val MapRoadMajor = Color(0xFF4A6076)
    val MapWater = Color(0xFF131E2A)
    val MapPark = Color(0xFF1E3A32)
    val MapLabel = Color(0xFF7C8DA6)

    // text
    val TextPrimary = Color(0xFF262626)
    val TextSecondary = Color(0xFF595959)
    val TextDisabled = Color(0xFFBFBFBF)
    val TextInverse = Color(0xFFFFFFFF)

    // status
    val StatusSuccess = Color(0xFF52C41A)
    val StatusWarning = Color(0xFFFAAD14)
    val StatusError = Color(0xFFFF4D4F)
    val StatusInfo = Color(0xFF1677FF)
    val StatusUnknown = Color(0xFF8C8C8C)

    val StatusSuccessBg = Color(0xFFF6FFED)
    val StatusWarningBg = Color(0xFFFFFBE6)
    val StatusErrorBg = Color(0xFFFFF2F0)
    val StatusInfoBg = Color(0xFFE6F4FF)
    val StatusUnknownBg = Color(0xFFF0F0F0)

    val BorderDefault = Color(0xFFD9D9D9)
    val DividerSubtle = Color(0xFFF0F0F0)

    /** Цвет текста предупреждающего Tag из tag.uicomponent. */
    val TagWarningText = Color(0xFFAD6800)
}
