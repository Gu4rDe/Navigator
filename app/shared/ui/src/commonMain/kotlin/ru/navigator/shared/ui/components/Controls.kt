package ru.navigator.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.navigator.shared.ui.theme.HairlineBorder
import ru.navigator.shared.ui.theme.NavigatorColors
import ru.navigator.shared.ui.theme.NavigatorRadius
import ru.navigator.shared.ui.theme.NavigatorType
import ru.navigator.shared.ui.theme.PillShape

enum class NvTagTone(val container: Color, val dot: Color, val label: Color) {
    Info(NavigatorColors.StatusInfoBg, NavigatorColors.StatusInfo, NavigatorColors.StatusInfo),
    Success(NavigatorColors.StatusSuccessBg, NavigatorColors.StatusSuccess, NavigatorColors.StatusSuccess),
    Warning(NavigatorColors.StatusWarningBg, NavigatorColors.StatusWarning, NavigatorColors.TagWarningText),
    Error(NavigatorColors.StatusErrorBg, NavigatorColors.StatusError, NavigatorColors.StatusError),
    Unknown(NavigatorColors.StatusUnknownBg, NavigatorColors.StatusUnknown, NavigatorColors.TextSecondary),
    Live(NavigatorColors.StatusSuccess, NavigatorColors.TextInverse, NavigatorColors.TextInverse),
    Replay(NavigatorColors.StatusWarning, NavigatorColors.TextInverse, NavigatorColors.TextInverse),
}

/** Компонент `tag` (бейджи LIVE/REPLAY/stale и статусные тона). */
@Composable
fun NvTag(
    label: String,
    modifier: Modifier = Modifier,
    tone: NvTagTone = NvTagTone.Info,
    dotVisible: Boolean = true,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(NavigatorRadius.Sm))
            .background(tone.container)
            .padding(vertical = 3.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (dotVisible) {
            Box(Modifier.size(6.dp).background(tone.dot, PillShape))
        }
        BasicText(text = label, style = NavigatorType.Badge.copy(color = tone.label))
    }
}

enum class NvInputState { Default, Focus, Error, Disabled }

/** Компонент `input` (строка поиска 48dp; состояния focus/error/disabled). */
@Composable
fun NvInput(
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    state: NvInputState = NvInputState.Default,
    label: String? = null,
) {
    val stroke = when (state) {
        NvInputState.Focus -> androidx.compose.foundation.BorderStroke(2.dp, NavigatorColors.BrandPrimary)
        NvInputState.Error -> androidx.compose.foundation.BorderStroke(2.dp, NavigatorColors.StatusError)
        else -> HairlineBorder
    }
    val boxColor = if (state == NvInputState.Disabled) NavigatorColors.SurfaceBase else NavigatorColors.SurfaceCard
    val valueColor = if (state == NvInputState.Disabled) NavigatorColors.TextDisabled else NavigatorColors.TextPrimary
    val shape = RoundedCornerShape(NavigatorRadius.Md)
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(6.dp)) {
        if (label != null) {
            BasicText(text = label, style = NavigatorType.Caption)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(shape)
                .background(boxColor)
                .border(stroke, shape)
                .clickable(onClick = onClick)
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NvGlyph(NvIcon.MagnifyingGlass, NavigatorColors.TextDisabled, Modifier.size(18.dp))
            BasicText(
                text = value,
                style = NavigatorType.Body.copy(color = valueColor),
                maxLines = 1,
                modifier = Modifier.weight(1f),
            )
        }
    }
}

enum class NvAlertTone(val container: Color, val icon: NvIcon, val iconTint: Color) {
    Info(NavigatorColors.StatusInfoBg, NvIcon.CircleInfo, NavigatorColors.StatusInfo),
    Success(NavigatorColors.StatusSuccessBg, NvIcon.CircleCheck, NavigatorColors.StatusSuccess),
    Warning(NavigatorColors.StatusWarningBg, NvIcon.TriangleExclamation, NavigatorColors.StatusWarning),
    Error(NavigatorColors.StatusErrorBg, NvIcon.CircleExclamation, NavigatorColors.StatusError),
}

/** Компонент `alert`. */
@Composable
fun NvAlert(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    tone: NvAlertTone = NvAlertTone.Info,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(NavigatorRadius.Md))
            .background(tone.container)
            .padding(vertical = 12.dp, horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        NvGlyph(tone.icon, tone.iconTint, Modifier.size(18.dp))
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            BasicText(text = title, style = NavigatorType.Strong)
            BasicText(text = message, style = NavigatorType.Caption)
        }
    }
}

/** Компонент `list-item` (иконка, заголовок, подпись, trailing). */
@Composable
fun NvListItem(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: NvIcon? = null,
    leadingTint: Color = NavigatorColors.StatusInfo,
    trailingIcon: NvIcon? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(NavigatorRadius.Lg))
            .background(NavigatorColors.SurfaceCard)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIcon != null) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(NavigatorColors.StatusInfoBg, PillShape),
                contentAlignment = Alignment.Center,
            ) {
                NvGlyph(leadingIcon, leadingTint, Modifier.size(16.dp))
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            BasicText(text = title, style = NavigatorType.Strong, maxLines = 1)
            BasicText(text = subtitle, style = NavigatorType.Caption, maxLines = 1)
        }
        if (trailingIcon != null) {
            NvGlyph(trailingIcon, NavigatorColors.TextDisabled, Modifier.size(14.dp))
        }
    }
}

/** Компонент `switch`. */
@Composable
fun NvSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(44.dp, 26.dp)
            .clip(PillShape)
            .background(if (checked) NavigatorColors.BrandPrimary else NavigatorColors.TextDisabled)
            .clickable { onCheckedChange(!checked) },
    ) {
        Box(
            modifier = Modifier
                .align(if (checked) Alignment.CenterEnd else Alignment.CenterStart)
                .padding(horizontal = 3.dp)
                .size(20.dp)
                .background(NavigatorColors.SurfaceCard, PillShape),
        )
    }
}

/** Компонент `segmented` (три сегмента). */
@Composable
fun NvSegmented(
    labels: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(NavigatorRadius.Md))
            .background(NavigatorColors.DividerSubtle)
            .padding(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        labels.forEachIndexed { index, text ->
            val selected = index == selectedIndex
            val shape = RoundedCornerShape(NavigatorRadius.Sm)
            Box(
                modifier = Modifier
                    .height(32.dp)
                    .clip(shape)
                    .background(if (selected) NavigatorColors.SurfaceCard else Color.Transparent)
                    .clickable { onSelect(index) }
                    .padding(horizontal = 14.dp),
                contentAlignment = Alignment.Center,
            ) {
                BasicText(
                    text = text,
                    style = NavigatorType.Badge.copy(
                        color = if (selected) NavigatorColors.TextPrimary else NavigatorColors.TextSecondary,
                    ),
                )
            }
        }
    }
}

/** Компонент `setting-row` (строка настроек со слотом контрола). */
@Composable
fun NvSettingRow(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    titleColor: Color = NavigatorColors.TextPrimary,
    onClick: (() -> Unit)? = null,
    control: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(NavigatorColors.SurfaceCard)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(vertical = 13.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            BasicText(text = title, style = NavigatorType.Body.copy(color = titleColor), maxLines = 1)
            BasicText(text = subtitle, style = NavigatorType.Caption, maxLines = 2)
        }
        control()
    }
}

/** Горизонтальный разделитель `divider.subtle` с отступами из макета. */
@Composable
fun NvDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(1.dp)
            .background(NavigatorColors.DividerSubtle),
    )
}
