package ru.navigator.shared.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.navigator.shared.ui.components.DesignStatusBar
import ru.navigator.shared.ui.components.NvButton
import ru.navigator.shared.ui.components.NvButtonKind
import ru.navigator.shared.ui.components.NvButtonSize
import ru.navigator.shared.ui.components.NvDivider
import ru.navigator.shared.ui.components.NvGlyph
import ru.navigator.shared.ui.components.NvIcon
import ru.navigator.shared.ui.components.NvIconButton
import ru.navigator.shared.ui.components.NvIconButtonKind
import ru.navigator.shared.ui.components.NvMapBase
import ru.navigator.shared.ui.components.NvSegmented
import ru.navigator.shared.ui.components.NvSettingRow
import ru.navigator.shared.ui.components.NvSwitch
import ru.navigator.shared.ui.components.NvTag
import ru.navigator.shared.ui.components.NvTagTone
import ru.navigator.shared.ui.components.mainRoutePath
import ru.navigator.shared.ui.theme.HairlineBorder
import ru.navigator.shared.ui.theme.NavigatorColors
import ru.navigator.shared.ui.theme.NavigatorType
import ru.navigator.shared.ui.theme.PillShape
import ru.navigator.shared.ui.theme.cardShadow
import ru.navigator.shared.ui.theme.overlayShadow

// ═══ 5. Построение маршрута (screen.build) ═══

@Composable
fun BuildScreen(onBack: () -> Unit, onBuild: () -> Unit) {
    ScreenFrame(NavigatorColors.SurfaceBase) {
        Column(Modifier.fillMaxSize()) {
            DesignStatusBar(time = "14:32", light = false)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NvIconButton(NvIcon.ArrowLeft, NavigatorColors.TextPrimary, onBack, modifier = Modifier.size(44.dp))
                BasicText("Маршрут", style = NavigatorType.Subtitle, modifier = Modifier.weight(1f))
                NvTag("LIVE", tone = NvTagTone.Live)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavigatorColors.SurfaceCard)
                    .border(HairlineBorder, RoundedCornerShape(12.dp)),
            ) {
                Column(Modifier.fillMaxWidth().padding(4.dp)) {
                    RouteEndpoint(
                        dot = { Box(Modifier.size(12.dp).background(NavigatorColors.StatusSuccess, PillShape)) },
                        caption = "Откуда",
                        value = "Текущая позиция",
                    )
                    NvDivider()
                    RouteEndpoint(
                        dot = { NvGlyph(NvIcon.LocationDot, NavigatorColors.StatusError, Modifier.size(13.dp)) },
                        caption = "Куда",
                        value = "Тверская ул., 15",
                    )
                }
                NvIconButton(
                    NvIcon.ArrowRightArrowLeft,
                    NavigatorColors.BrandPrimary,
                    {},
                    modifier = Modifier
                        .offset(300.dp, 40.dp)
                        .size(40.dp)
                        .clip(PillShape)
                        .border(HairlineBorder, PillShape)
                        .background(NavigatorColors.SurfaceCard),
                    large = false,
                    iconRotation = 90f,
                )
            }
            CardGroup(Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)) {
                NvSettingRow(
                    "Избегать платных",
                    "Платные участки и туннели",
                    control = { NvSwitch(checked = false, onCheckedChange = {}) },
                )
                NvDivider()
                NvSettingRow(
                    "Избегать пробок",
                    "Пересчёт по LIVE-трафику",
                    control = { NvSwitch(checked = true, onCheckedChange = {}) },
                )
                NvDivider()
                NvSettingRow(
                    "Исключить дороги без данных",
                    "Участки с покрытием unknown",
                    control = { NvSwitch(checked = true, onCheckedChange = {}) },
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp, start = 16.dp, end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BasicText("Транспорт", style = NavigatorType.Caption, modifier = Modifier.weight(1f))
                NvSegmented(listOf("Авто", "Пешком", "Вело"), selectedIndex = 0, onSelect = {})
            }
            NvSettingRow(
                title = "Отправление",
                subtitle = "Сейчас, 14:32",
                control = { NvGlyph(NvIcon.ChevronRight, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp).clip(RoundedCornerShape(12.dp)),
            )
            NvSettingRow(
                title = "Учебный сценарий",
                subtitle = "Симуляция без GNSS, будет помечена",
                control = { NvSwitch(checked = false, onCheckedChange = {}) },
                modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp).clip(RoundedCornerShape(12.dp)),
            )
            Spacer(Modifier.weight(1f))
            Column(Modifier.fillMaxWidth().padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 40.dp)) {
                NvButton(
                    label = "Построить маршрут",
                    onClick = onBuild,
                    modifier = Modifier.fillMaxWidth(),
                    size = NvButtonSize.Large,
                )
            }
        }
    }
}

@Composable
private fun RouteEndpoint(dot: @Composable () -> Unit, caption: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        dot()
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            BasicText(caption, style = NavigatorType.Caption)
            BasicText(value, style = NavigatorType.Strong)
        }
    }
}

// ═══ 6. Предпросмотр маршрута (screen.preview) ═══

@Composable
fun PreviewScreen(onBack: () -> Unit, onStart: () -> Unit, onSimulate: () -> Unit) {
    ScreenFrame(NavigatorColors.MapCanvas) {
        NvMapBase {
            OverlayPath(
                Path().apply {
                    moveTo(78f, 844f); lineTo(78f, 560f)
                    quadraticTo(78f, 528f, 110f, 528f); lineTo(318f, 528f)
                },
                NavigatorColors.MapRoadMajor, 6f,
            )
            OverlayPath(
                Path().apply {
                    moveTo(198f, 844f); lineTo(198f, 380f)
                    quadraticTo(198f, 340f, 238f, 340f)
                    lineTo(318f, 340f); lineTo(318f, 528f)
                },
                NavigatorColors.MapRoadMajor, 6f,
            )
            OverlayPath(mainRoutePath(), NavigatorColors.BrandPrimary, 8f)
            OverlayPath(
                Path().apply { moveTo(198f, 700f); lineTo(198f, 640f) },
                NavigatorColors.StatusWarning, 8f,
            )
            PositionMarker(187, 689, accuracy = false)
            DestinationPin(305, 494)
        }
        DesignStatusBar(time = "14:32", light = true)
        NvIconButton(
            NvIcon.ArrowLeft,
            NavigatorColors.TextPrimary,
            onBack,
            modifier = Modifier.offset(16.dp, 72.dp),
        )
        Row(
            modifier = Modifier
                .offset(72.dp, 72.dp)
                .cardShadow(PillShape)
                .clip(PillShape)
                .background(NavigatorColors.SurfaceCard)
                .padding(horizontal = 16.dp)
                .height(44.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicText("Варианты маршрута", style = NavigatorType.Strong)
            NvTag("LIVE", tone = NvTagTone.Live)
        }
        Box(Modifier.align(Alignment.BottomStart)) {
            SheetColumn(height = 416, gap = 10) {
                BasicText("3 варианта, данные на 14:32", style = NavigatorType.Caption)
                RouteOptionRow(
                    eta = "32 мин",
                    meta = "18,4 км, в пути 28 мин, пробка 4 мин",
                    tagLabel = "Быстрый",
                    tagTone = NvTagTone.Success,
                    selected = true,
                )
                RouteOptionRow(
                    eta = "38 мин",
                    meta = "19,2 км, без платных и без участков unknown",
                    tagLabel = "Спокойный",
                    tagTone = NvTagTone.Info,
                    selected = false,
                )
                RouteOptionRow(
                    eta = "41 мин",
                    meta = "17,1 км, проезжает зону ДТП",
                    tagLabel = "События",
                    tagTone = NvTagTone.Warning,
                    selected = false,
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    NvButton(
                        label = "Начать ведение",
                        onClick = onStart,
                        modifier = Modifier.weight(1f),
                        size = NvButtonSize.Large,
                        icon = NvIcon.Play,
                    )
                    NvButton(
                        label = "Симуляция",
                        onClick = onSimulate,
                        size = NvButtonSize.Large,
                        kind = NvButtonKind.Secondary,
                    )
                }
            }
        }
    }
}

@Composable
private fun RouteOptionRow(
    eta: String,
    meta: String,
    tagLabel: String,
    tagTone: NvTagTone,
    selected: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(NavigatorColors.SurfaceCard)
            .then(
                if (selected) Modifier.border(2.dp, NavigatorColors.BrandPrimary, RoundedCornerShape(12.dp))
                else Modifier.border(HairlineBorder, RoundedCornerShape(12.dp))
            )
            .padding(vertical = 12.dp, horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.width(230.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            BasicText(eta, style = NavigatorType.Title)
            BasicText(meta, style = NavigatorType.Caption)
        }
        NvTag(tagLabel, tone = tagTone, dotVisible = false)
    }
}

// ═══ 7. Активное ведение (screen.guidance) ═══

@Composable
fun GuidanceScreen(onFinish: () -> Unit) {
    ScreenFrame(NavigatorColors.MapCanvas) {
        NvMapBase {
            OverlayPath(mainRoutePath(), NavigatorColors.StatusUnknown, 8f)
            OverlayPath(
                Path().apply {
                    moveTo(198f, 716f); lineTo(198f, 560f)
                    quadraticTo(198f, 528f, 230f, 528f); lineTo(318f, 528f)
                },
                NavigatorColors.BrandPrimary, 8f,
            )
            PositionMarker(187, 689, accuracy = false)
            DestinationPin(305, 494)
        }
        DesignStatusBar(time = "14:32", light = true)
        Row(
            modifier = Modifier
                .offset(16.dp, 72.dp)
                .width(358.dp)
                .overlayShadow(RoundedCornerShape(16.dp))
                .clip(RoundedCornerShape(16.dp))
                .background(NavigatorColors.SurfaceCard)
                .padding(14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                Modifier.size(52.dp).background(NavigatorColors.BrandPrimary, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center,
            ) {
                NvGlyph(NvIcon.ArrowRight, NavigatorColors.TextInverse, Modifier.size(26.dp))
            }
            Column(Modifier.width(196.dp), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                BasicText(
                    "Через 300 м",
                    style = NavigatorType.Caption.copy(
                        color = NavigatorColors.BrandPrimary,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                    ),
                )
                BasicText("Поверните направо", style = NavigatorType.Maneuver)
                BasicText("ул. Охотный Ряд", style = NavigatorType.Body.copy(color = NavigatorColors.TextSecondary))
            }
            NvIconButton(
                NvIcon.VolumeXmark,
                NavigatorColors.TextSecondary,
                {},
                modifier = Modifier.size(44.dp),
                elevated = false,
            )
        }
        Column(
            modifier = Modifier
                .offset(330.dp, 280.dp)
                .width(44.dp)
                .cardShadow(PillShape)
                .clip(PillShape)
                .background(NavigatorColors.SurfaceCard),
        ) {
            Box(Modifier.fillMaxWidth().height(44.dp).clickable { }, contentAlignment = Alignment.Center) {
                BasicText("+", style = NavigatorType.Title.copy(color = NavigatorColors.TextSecondary))
            }
            Box(Modifier.fillMaxWidth().padding(horizontal = 10.dp).height(1.dp).background(NavigatorColors.DividerSubtle))
            Box(Modifier.fillMaxWidth().height(44.dp).clickable { }, contentAlignment = Alignment.Center) {
                BasicText("−", style = NavigatorType.Title.copy(color = NavigatorColors.TextSecondary))
            }
        }
        Box(Modifier.align(Alignment.BottomStart)) {
            SheetColumn(height = 284) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column { BasicText("14:58", style = NavigatorType.Eta); BasicText("прибытие", style = NavigatorType.Caption) }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        BasicText("26 мин", style = NavigatorType.Title)
                        BasicText("осталось", style = NavigatorType.Caption)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        BasicText("12,4 км", style = NavigatorType.Title)
                        BasicText("впереди", style = NavigatorType.Caption)
                    }
                }
                Box(
                    Modifier.fillMaxWidth().height(6.dp).background(NavigatorColors.DividerSubtle, PillShape),
                ) {
                    Box(Modifier.width(128.dp).height(6.dp).background(NavigatorColors.BrandPrimary, PillShape))
                }
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    BasicText("Далее", style = NavigatorType.Caption)
                    NextManeuverRow(NvIcon.ArrowLeft, "Через 1,2 км, налево на ул. Тверская")
                    NextManeuverRow(NvIcon.FlagCheckered, "Через 3,8 км, прибытие справа")
                }
                NvButton(
                    label = "Завершить маршрут",
                    onClick = onFinish,
                    modifier = Modifier.fillMaxWidth(),
                    kind = NvButtonKind.Secondary,
                )
            }
        }
    }
}

@Composable
private fun NextManeuverRow(icon: NvIcon, text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NvGlyph(icon, NavigatorColors.TextSecondary, Modifier.size(14.dp))
        BasicText(text, style = NavigatorType.Caption.copy(color = NavigatorColors.TextPrimary), modifier = Modifier.width(300.dp))
    }
}

// ═══ 8. Прибытие (screen.arrival) ═══

@Composable
fun ArrivalScreen(onToMap: () -> Unit, onReverse: () -> Unit) {
    ScreenFrame(NavigatorColors.SurfaceBase) {
        Column(Modifier.fillMaxSize()) {
            DesignStatusBar(time = "14:58", light = false)
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 36.dp, start = 24.dp, end = 24.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    Modifier.size(88.dp).background(NavigatorColors.StatusSuccessBg, PillShape),
                    contentAlignment = Alignment.Center,
                ) {
                    NvGlyph(NvIcon.Check, NavigatorColors.StatusSuccess, Modifier.size(40.dp))
                }
                BasicText("Вы приехали", style = NavigatorType.H1)
                BasicText(
                    "Тверская ул., 15, прибытие 14:58",
                    style = NavigatorType.Body.copy(color = NavigatorColors.TextSecondary),
                )
                NvTag("LIVE, обновления остановлены", tone = NvTagTone.Live)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavigatorColors.SurfaceCard)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    ArrivalStat("18,4 км", "расстояние", Modifier.weight(1f))
                    ArrivalStat("31 мин", "время в пути", Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    ArrivalStat("36 км/ч", "средняя скорость", Modifier.weight(1f))
                    ArrivalStat("+3 мин", "отклонение от плана", Modifier.weight(1f), valueColor = NavigatorColors.StatusWarning)
                }
            }
            Spacer(Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                NvButton("Вернуться на карту", onToMap, Modifier.fillMaxWidth(), size = NvButtonSize.Large)
                NvButton("Обратный маршрут", onReverse, Modifier.fillMaxWidth(), kind = NvButtonKind.Secondary)
                NvButton("Оценить поездку", {}, Modifier.fillMaxWidth(), kind = NvButtonKind.Ghost)
            }
        }
    }
}

@Composable
private fun ArrivalStat(value: String, label: String, modifier: Modifier = Modifier, valueColor: Color = NavigatorColors.TextPrimary) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(2.dp)) {
        BasicText(value, style = NavigatorType.Title.copy(color = valueColor))
        BasicText(label, style = NavigatorType.Caption)
    }
}
