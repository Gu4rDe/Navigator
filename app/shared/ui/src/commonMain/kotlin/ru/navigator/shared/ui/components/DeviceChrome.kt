package ru.navigator.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.navigator.shared.ui.theme.PillShape

/**
 * Декоративный статус-бар из макета (ios-status-bar): время и иконки
 * сигнал/wifi/батарея. `light` — белый вариант для экранов с картой.
 */
@Composable
fun DesignStatusBar(time: String, light: Boolean, modifier: Modifier = Modifier) {
    val tint = if (light) Color.White else Color.Black
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(59.dp)
            .padding(horizontal = 27.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.width(81.dp), contentAlignment = Alignment.Center) {
            BasicText(
                text = time,
                style = TextStyle(
                    fontSize = 17.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = tint,
                ),
            )
        }
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            NvGlyph(NvIcon.SbSignal, tint, Modifier.size(18.dp, 12.dp))
            NvGlyph(NvIcon.SbWifi, tint, Modifier.size(17.dp, 12.dp))
            NvGlyph(NvIcon.SbBattery, tint, Modifier.size(27.dp, 13.dp))
        }
    }
}

/** Декоративный индикатор Home из макета (ios-home-indicator). */
@Composable
fun DesignHomeIndicator(light: Boolean, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(134.dp, 5.dp)
            .background(if (light) Color.White else Color.Black, PillShape),
    )
}
