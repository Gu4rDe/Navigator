package ru.navigator.shared.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

/** Тонкая обводка `stroke.hairline` из KUI tokens. */
val HairlineBorder = BorderStroke(1.dp, NavigatorColors.BorderDefault)

/** Приближение `shadow.card` (#0000000F, offset 0/4, blur 12). */
fun Modifier.cardShadow(shape: Shape): Modifier =
    shadow(4.dp, shape, ambientColor = Color(0x0F000000), spotColor = Color(0x0F000000))

/** Приближение `shadow.overlay` (#00000029, offset 0/8, blur 24). */
fun Modifier.overlayShadow(shape: Shape): Modifier =
    shadow(8.dp, shape, ambientColor = Color(0x29000000), spotColor = Color(0x29000000))

/** Скругление «pill» из KUI (`radius.pill` = 999). */
val PillShape = RoundedCornerShape(percent = 50)
