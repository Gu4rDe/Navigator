package ru.navigator.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.navigator.shared.ui.theme.HairlineBorder
import ru.navigator.shared.ui.theme.NavigatorColors
import ru.navigator.shared.ui.theme.NavigatorRadius
import ru.navigator.shared.ui.theme.NavigatorType
import ru.navigator.shared.ui.theme.PillShape
import ru.navigator.shared.ui.theme.cardShadow

enum class NvButtonKind { Primary, Secondary, Ghost, Danger, Dark }

enum class NvButtonSize { Default, Large }

/** Компонент `button` из дизайн-системы (kinds: primary/secondary/ghost/danger/dark; 40/48; disabled). */
@Composable
fun NvButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    kind: NvButtonKind = NvButtonKind.Primary,
    size: NvButtonSize = NvButtonSize.Default,
    enabled: Boolean = true,
    icon: NvIcon? = null,
    iconTint: Color? = null,
) {
    val large = size == NvButtonSize.Large
    val background = when (kind) {
        NvButtonKind.Primary -> NavigatorColors.BrandPrimary
        NvButtonKind.Secondary -> NavigatorColors.SurfaceCard
        NvButtonKind.Ghost -> Color.Transparent
        NvButtonKind.Danger -> NavigatorColors.StatusError
        NvButtonKind.Dark -> NavigatorColors.MapCanvas
    }
    val labelColor = when (kind) {
        NvButtonKind.Primary, NvButtonKind.Danger, NvButtonKind.Dark -> NavigatorColors.TextInverse
        NvButtonKind.Secondary -> NavigatorColors.TextPrimary
        NvButtonKind.Ghost -> NavigatorColors.BrandPrimary
    }
    val shape = RoundedCornerShape(if (large) NavigatorRadius.Lg else NavigatorRadius.Md)
    Row(
        modifier = modifier
            .height(if (large) 48.dp else 40.dp)
            .alpha(if (enabled) 1f else 0.35f)
            .clip(shape)
            .background(background)
            .then(if (kind == NvButtonKind.Secondary) Modifier.border(HairlineBorder, shape) else Modifier)
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = if (large) 20.dp else 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) {
            NvGlyph(icon, iconTint ?: labelColor, Modifier.size(if (large) 16.dp else 14.dp))
        }
        BasicText(
            text = label,
            style = NavigatorType.Strong.copy(
                color = labelColor,
                fontSize = if (large) 17.sp else 15.sp,
            ),
        )
    }
}

enum class NvIconButtonKind { Default, Primary, Dark }

/** Компонент `icon-button` (44/56, badge, primary/dark). */
@Composable
fun NvIconButton(
    icon: NvIcon,
    iconTint: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    kind: NvIconButtonKind = NvIconButtonKind.Default,
    large: Boolean = false,
    badge: Boolean = false,
    elevated: Boolean = true,
    iconRotation: Float = 0f,
) {
    val side = if (large) 56.dp else 44.dp
    val background = when (kind) {
        NvIconButtonKind.Default -> NavigatorColors.SurfaceCard
        NvIconButtonKind.Primary -> NavigatorColors.BrandPrimary
        NvIconButtonKind.Dark -> NavigatorColors.MapCanvas
    }
    Box(
        modifier = modifier
            .size(side)
            .then(if (elevated) Modifier.cardShadow(PillShape) else Modifier)
            .clip(PillShape)
            .background(background)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        NvGlyph(
            icon,
            iconTint,
            Modifier
                .size(if (large) 22.dp else 20.dp)
                .rotate(iconRotation),
        )
        if (badge) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(28.dp, 6.dp)
                    .size(10.dp)
                    .background(NavigatorColors.StatusError, PillShape)
                    .border(2.dp, NavigatorColors.SurfaceCard, PillShape),
            )
        }
    }
}
