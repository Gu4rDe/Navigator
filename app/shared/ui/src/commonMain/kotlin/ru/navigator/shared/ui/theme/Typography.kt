package ru.navigator.shared.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Типографика из KUI tokens. Шрифт Helvetica Neue в дизайн-проекте не встроен
 * (нет customFonts), поэтому используется системный гротеск платформы.
 */
object NavigatorType {
    private val family = FontFamily.SansSerif

    val Caption = TextStyle(
        fontFamily = family, fontSize = 13.sp, lineHeight = 18.sp,
        fontWeight = FontWeight.Normal, color = NavigatorColors.TextSecondary,
    )
    val Body = TextStyle(
        fontFamily = family, fontSize = 15.sp, lineHeight = 22.sp,
        fontWeight = FontWeight.Normal, color = NavigatorColors.TextPrimary,
    )
    val Strong = TextStyle(
        fontFamily = family, fontSize = 15.sp, lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold, color = NavigatorColors.TextPrimary,
    )
    val Subtitle = TextStyle(
        fontFamily = family, fontSize = 17.sp, lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold, color = NavigatorColors.TextPrimary,
    )
    val Title = TextStyle(
        fontFamily = family, fontSize = 20.sp, lineHeight = 28.sp,
        fontWeight = FontWeight.SemiBold, color = NavigatorColors.TextPrimary,
    )
    val H1 = TextStyle(
        fontFamily = family, fontSize = 24.sp, lineHeight = 32.sp,
        fontWeight = FontWeight.Bold, color = NavigatorColors.TextPrimary,
    )
    val Maneuver = TextStyle(
        fontFamily = family, fontSize = 24.sp, lineHeight = 30.sp,
        fontWeight = FontWeight.Bold, color = NavigatorColors.TextPrimary,
    )
    val Eta = TextStyle(
        fontFamily = family, fontSize = 28.sp, lineHeight = 34.sp,
        fontWeight = FontWeight.Bold, color = NavigatorColors.TextPrimary,
    )
    val Badge = TextStyle(
        fontFamily = family, fontSize = 13.sp, lineHeight = 16.sp,
        fontWeight = FontWeight.SemiBold, color = NavigatorColors.TextPrimary,
    )
}
