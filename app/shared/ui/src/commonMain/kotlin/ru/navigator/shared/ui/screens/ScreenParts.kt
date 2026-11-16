package ru.navigator.shared.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import ru.navigator.shared.ui.components.DesignHomeIndicator
import ru.navigator.shared.ui.components.NvGlyph
import ru.navigator.shared.ui.components.NvIcon
import ru.navigator.shared.ui.theme.NavigatorColors
import ru.navigator.shared.ui.theme.NavigatorType
import ru.navigator.shared.ui.theme.PillShape
import ru.navigator.shared.ui.theme.overlayShadow

/** Корень экрана макета 390×844 с домашним индикатором поверх. */
@Composable
fun ScreenFrame(
    background: Color,
    content: @Composable androidx.compose.foundation.layout.BoxScope.() -> Unit,
) {
    Box(Modifier.fillMaxSize().background(background)) {
        content()
        DesignHomeIndicator(light = false, modifier = Modifier.offset(128.dp, 827.dp))
    }
}

/** Нижний bottom-sheet из макета: белая карта с верхним скруглением 20 и тенью overlay. */
@Composable
fun SheetColumn(
    height: Int,
    modifier: Modifier = Modifier,
    gap: Int = 12,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .overlayShadow(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(NavigatorColors.SurfaceCard)
            .padding(top = 10.dp, start = 16.dp, end = 16.dp, bottom = 34.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(gap.dp),
    ) {
        SheetGrabber()
        content()
    }
}

@Composable
private fun SheetGrabber() {
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Box(
            Modifier
                .size(36.dp, 4.dp)
                .background(NavigatorColors.BorderDefault, PillShape),
        )
    }
}

/** Путь-оверлей на карте в координатах макета (например, линия маршрута). */
@Composable
fun OverlayPath(path: Path, color: Color, width: Float) {
    Canvas(Modifier.fillMaxSize()) {
        drawPath(path, color, style = Stroke(width = width, cap = StrokeCap.Round))
    }
}

/** Маркер текущей позиции: круг точности + точка. */
@Composable
fun PositionMarker(x: Int, y: Int, accuracy: Boolean = true) {
    if (accuracy) {
        Box(
            Modifier
                .offset(x.dp, y.dp)
                .size(64.dp)
                .background(Color(0x261677FF), PillShape),
        )
    }
    Box(
        Modifier
            .offset((x + 21).dp, (y + 21).dp)
            .size(22.dp)
            .background(NavigatorColors.BrandPrimary, PillShape)
            .border(3.dp, NavigatorColors.SurfaceCard, PillShape),
    )
}

/** Красный пин назначения (fas:location-dot). */
@Composable
fun DestinationPin(x: Int, y: Int, width: Int = 26, height: Int = 34) {
    NvGlyph(
        NvIcon.LocationDot,
        NavigatorColors.StatusError,
        Modifier.offset(x.dp, y.dp).size(width.dp, height.dp),
    )
}

/** Маркер события ИТС на карте (кружок с car-burst). */
@Composable
fun EventMarker(x: Int, y: Int) {
    Box(
        Modifier
            .offset(x.dp, y.dp)
            .size(28.dp)
            .background(NavigatorColors.StatusWarning, PillShape)
            .border(2.dp, NavigatorColors.SurfaceCard, PillShape),
        contentAlignment = Alignment.Center,
    ) {
        NvGlyph(NvIcon.CarBurst, NavigatorColors.TextInverse, Modifier.size(14.dp))
    }
}

/** Подпись секции с полями из макета. */
@Composable
fun SectionCaption(text: String, modifier: Modifier = Modifier) {
    BasicText(
        text = text,
        style = NavigatorType.Caption,
        modifier = modifier.fillMaxWidth(),
    )
}

/** Белая карта-группа списка со скруглением lg. */
@Composable
fun CardGroup(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(NavigatorColors.SurfaceCard),
        content = content,
    )
}
