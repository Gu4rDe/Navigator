package ru.navigator.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.navigator.shared.ui.theme.NavigatorColors
import ru.navigator.shared.ui.theme.NavigatorType

/** Дизайн-вьюпорт мобильного макета: 390×844. */
const val DESIGN_WIDTH = 390
const val DESIGN_HEIGHT = 844

/**
 * Масштабирует дизайн-координаты 390×844 под фактический размер окна/экрана
 * с сохранением пропорций (letterbox на фоне [background]).
 */
@Composable
fun DesignViewport(
    background: Color,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    BoxWithConstraints(modifier = modifier.fillMaxSize().background(background)) {
        val scale = minOf(maxWidth / DESIGN_WIDTH.dp, maxHeight / DESIGN_HEIGHT.dp)
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(DESIGN_WIDTH.dp, DESIGN_HEIGHT.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                },
        ) {
            content()
        }
    }
}

/**
 * Компонент `map-base`: стилизованный векторный слой карты 390×844 без внешних
 * тайлов. Слой [overlays] — инъекция `map.overlays` из KUI.
 */
@Composable
fun NvMapBase(
    modifier: Modifier = Modifier,
    overlays: @Composable BoxScope.() -> Unit = {},
) {
    Box(modifier = modifier.size(DESIGN_WIDTH.dp, DESIGN_HEIGHT.dp).clipToBounds()) {
        androidx.compose.foundation.Canvas(Modifier.fillMaxSize()) {
            drawRect(NavigatorColors.MapCanvas)

            // map.water — повёрнутый прямоугольник
            rotate(24f, pivot = Offset(-140f + 110f, 430f + 450f)) {
                drawRect(NavigatorColors.MapWater, Offset(-140f, 430f), Size(220f, 900f))
            }

            // map.blocks
            val blocks = listOf(
                listOf(96f, 168f, 84f, 148f),
                listOf(216f, 168f, 84f, 148f),
                listOf(16f, 360f, 44f, 148f),
                listOf(216f, 360f, 84f, 148f),
                listOf(336f, 168f, 38f, 340f),
                listOf(96f, 736f, 84f, 92f),
                listOf(216f, 736f, 84f, 92f),
                listOf(336f, 552f, 38f, 276f),
            )
            blocks.forEach { b ->
                drawRoundRect(
                    NavigatorColors.MapBlock,
                    Offset(b[0], b[1]),
                    Size(b[2], b[3]),
                    androidx.compose.ui.geometry.CornerRadius(10f),
                )
            }

            // map.park
            drawRoundRect(
                NavigatorColors.MapPark,
                Offset(216f, 552f),
                Size(84f, 148f),
                androidx.compose.ui.geometry.CornerRadius(12f),
            )

            // map.roads
            fun vRoad(x: Float, width: Float, color: Color) = drawLine(color, Offset(x, 0f), Offset(x, 844f), strokeWidth = width)
            fun hRoad(y: Float, width: Float, color: Color) = drawLine(color, Offset(0f, y), Offset(390f, y), strokeWidth = width)

            vRoad(78f, 12f, NavigatorColors.MapRoadMajor)
            vRoad(198f, 8f, NavigatorColors.MapRoad)
            vRoad(318f, 12f, NavigatorColors.MapRoadMajor)
            hRoad(148f, 12f, NavigatorColors.MapRoadMajor)
            hRoad(340f, 8f, NavigatorColors.MapRoad)
            hRoad(528f, 12f, NavigatorColors.MapRoadMajor)
            hRoad(712f, 8f, NavigatorColors.MapRoad)
            // map.highway — диагональ
            drawLine(
                NavigatorColors.MapRoadMajor,
                Offset(-20f, 250f),
                Offset(-20f + 430f, 250f + 290f),
                strokeWidth = 16f,
            )
        }

        // map.labels
        MapLabel("ул. Пушкина", Modifier.offset(92.dp, 128.dp))
        MapLabel("пр. Мира", Modifier.offset(206.dp, 322.dp))
        MapLabel("наб. Речная", Modifier.offset(24.dp, 500.dp).rotate(336f))
        BasicText(
            text = "Парк",
            style = NavigatorType.Caption.copy(fontSize = 11.sp, color = Color(0xFF5F7A6E)),
            modifier = Modifier.offset(222.dp, 620.dp),
        )

        overlays()
    }
}

@Composable
private fun MapLabel(text: String, modifier: Modifier = Modifier) {
    BasicText(
        text = text,
        style = NavigatorType.Caption.copy(fontSize = 11.sp, color = NavigatorColors.MapLabel),
        modifier = modifier,
    )
}

/**
 * Маршрутная линия из KUI path (viewBox 390×844): «M198 844 L198 560 Q198 528 230 528 L318 528».
 * Рисуется поверх карты в координатах макета.
 */
fun mainRoutePath(): Path = Path().apply {
    moveTo(198f, 844f)
    lineTo(198f, 560f)
    quadraticTo(198f, 528f, 230f, 528f)
    lineTo(318f, 528f)
}
