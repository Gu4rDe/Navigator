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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.navigator.shared.ui.components.DesignStatusBar
import ru.navigator.shared.ui.components.NvButton
import ru.navigator.shared.ui.components.NvButtonKind
import ru.navigator.shared.ui.components.NvButtonSize
import ru.navigator.shared.ui.components.NvDivider
import ru.navigator.shared.ui.components.NvGlyph
import ru.navigator.shared.ui.components.NvIcon
import ru.navigator.shared.ui.components.NvIconButton
import ru.navigator.shared.ui.components.NvIconButtonKind
import ru.navigator.shared.ui.components.NvInput
import ru.navigator.shared.ui.components.NvInputState
import ru.navigator.shared.ui.components.NvListItem
import ru.navigator.shared.ui.components.NvMapBase
import ru.navigator.shared.ui.components.NvTag
import ru.navigator.shared.ui.components.NvTagTone
import ru.navigator.shared.ui.components.mainRoutePath
import ru.navigator.shared.ui.theme.HairlineBorder
import ru.navigator.shared.ui.theme.NavigatorColors
import ru.navigator.shared.ui.theme.NavigatorType
import ru.navigator.shared.ui.theme.PillShape
import ru.navigator.shared.ui.theme.cardShadow

// ═══ 1. Первый запуск (screen.welcome) ═══

@Composable
fun WelcomeScreen(
    onAllow: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenFrame(NavigatorColors.SurfaceBase, modifier) {
        Column(Modifier.fillMaxSize()) {
            DesignStatusBar(time = "14:32", light = false)
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp, start = 24.dp, end = 24.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    Modifier.size(88.dp).background(NavigatorColors.BrandBg, PillShape),
                    contentAlignment = Alignment.Center,
                ) {
                    NvGlyph(NvIcon.Route, NavigatorColors.BrandPrimary, Modifier.size(36.dp))
                }
                BasicText("Навигатор", style = NavigatorType.H1)
                BasicText(
                    "Маршруты, пробки и события на дороге",
                    style = NavigatorType.Body.copy(color = NavigatorColors.TextSecondary, textAlign = TextAlign.Center),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavigatorColors.SurfaceCard)
                    .border(HairlineBorder, RoundedCornerShape(12.dp))
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                BasicText("Геолокация", style = NavigatorType.Subtitle)
                BasicText("Для позиции на карте, ведения и пересчёта", style = NavigatorType.Caption)
                LocationOption(selected = true, title = "Точная геолокация", subtitle = "Ведение и голосовые подсказки")
                LocationOption(selected = false, title = "Приблизительная", subtitle = "Позиция с погрешностью до 500 м")
                LocationOption(selected = false, title = "Без геолокации", subtitle = "Ручной выбор точки старта")
            }
            BasicText(
                "Позиция обрабатывается на устройстве",
                style = NavigatorType.Caption.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp, start = 24.dp, end = 24.dp),
            )
            Spacer(Modifier.weight(1f))
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                NvButton(
                    label = "Разрешить геолокацию",
                    onClick = onAllow,
                    modifier = Modifier.fillMaxWidth(),
                    size = NvButtonSize.Large,
                )
                NvButton(
                    label = "Продолжить без геолокации",
                    onClick = onSkip,
                    modifier = Modifier.fillMaxWidth(),
                    kind = NvButtonKind.Ghost,
                )
            }
        }
    }
}

@Composable
private fun LocationOption(selected: Boolean, title: String, subtitle: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (selected) {
            Box(
                Modifier
                    .size(22.dp)
                    .border(2.dp, NavigatorColors.BrandPrimary, PillShape),
                contentAlignment = Alignment.Center,
            ) {
                Box(Modifier.size(12.dp).background(NavigatorColors.BrandPrimary, PillShape))
            }
        } else {
            Box(Modifier.size(22.dp).border(2.dp, NavigatorColors.BorderDefault, PillShape))
        }
        Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
            BasicText(title, style = NavigatorType.Strong)
            BasicText(subtitle, style = NavigatorType.Caption)
        }
    }
}

// ═══ 2. Основная карта (screen.map) ═══

@Composable
fun MapScreen(
    onSearch: () -> Unit,
    onBuildRoute: () -> Unit,
    onEvent: () -> Unit,
    onStatus: () -> Unit,
    onSettings: () -> Unit,
    onAbout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenFrame(NavigatorColors.MapCanvas, modifier) {
        NvMapBase {
            OverlayPath(mainRoutePath(), NavigatorColors.BrandPrimary, 8f)
            OverlayPath(
                androidx.compose.ui.graphics.Path().apply {
                    moveTo(198f, 756f)
                    lineTo(198f, 656f)
                },
                NavigatorColors.StatusWarning,
                8f,
            )
            OverlayPath(
                androidx.compose.ui.graphics.Path().apply {
                    moveTo(198f, 844f)
                    lineTo(198f, 768f)
                },
                NavigatorColors.StatusError,
                8f,
            )
            PositionMarker(166, 668)
            DestinationPin(305, 494)
            EventMarker(242, 316)
        }
        DesignStatusBar(time = "14:32", light = true)
        Row(
            modifier = Modifier
                .offset(16.dp, 72.dp)
                .width(358.dp)
                .height(48.dp)
                .cardShadow(RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(NavigatorColors.SurfaceCard)
                .clickable(onClick = onSearch)
                .padding(horizontal = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            NvGlyph(NvIcon.MagnifyingGlass, NavigatorColors.TextDisabled, Modifier.size(18.dp))
            BasicText(
                "Куда поедем?",
                style = NavigatorType.Body.copy(color = NavigatorColors.TextDisabled),
                modifier = Modifier.weight(1f),
            )
        }
        NvTag("LIVE", tone = NvTagTone.Live, modifier = Modifier.offset(16.dp, 132.dp))
        BasicText(
            "данные на 14:32",
            style = NavigatorType.Caption.copy(fontWeight = FontWeight.SemiBold, color = Color(0xCCFFFFFF)),
            modifier = Modifier.offset(88.dp, 136.dp),
        )
        Column(modifier = Modifier.offset(330.dp, 200.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            NvIconButton(NvIcon.TriangleExclamation, NavigatorColors.TextPrimary, onEvent, badge = true)
            NvIconButton(NvIcon.Signal, NavigatorColors.TextPrimary, onStatus)
            NvIconButton(NvIcon.Gear, NavigatorColors.TextPrimary, onSettings)
            NvIconButton(NvIcon.CircleInfo, NavigatorColors.TextPrimary, onAbout)
        }
        NvIconButton(
            NvIcon.Route,
            NavigatorColors.TextInverse,
            onBuildRoute,
            modifier = Modifier.offset(318.dp, 616.dp),
            kind = NvIconButtonKind.Primary,
            large = true,
        )
        Box(Modifier.align(Alignment.BottomStart)) {
            SheetColumn(height = 148) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NvButton("Домой", {}, kind = NvButtonKind.Secondary, icon = NvIcon.House, iconTint = NavigatorColors.TextSecondary)
                    NvButton("На работу", {}, kind = NvButtonKind.Secondary, icon = NvIcon.Briefcase, iconTint = NavigatorColors.TextSecondary)
                    NvButton("События рядом", {}, kind = NvButtonKind.Secondary, icon = NvIcon.TriangleExclamation, iconTint = NavigatorColors.TextSecondary)
                }
                BasicText(
                    "Пробки обновлены 14:32, маршрут до центра 32 мин",
                    style = NavigatorType.Caption,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

// ═══ 3. Поиск (screen.search) ═══

@Composable
fun SearchScreen(
    onBack: () -> Unit,
    onResult: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenFrame(NavigatorColors.SurfaceBase, modifier) {
        Column(Modifier.fillMaxSize()) {
            DesignStatusBar(time = "14:32", light = false)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NvIconButton(NvIcon.ArrowLeft, NavigatorColors.TextPrimary, onBack, modifier = Modifier.size(44.dp))
                NvInput("Тверская", {}, state = NvInputState.Focus, modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                NvButton("Дом", {}, kind = NvButtonKind.Secondary, icon = NvIcon.House, iconTint = NavigatorColors.TextSecondary)
                NvButton("Работа", {}, kind = NvButtonKind.Secondary, icon = NvIcon.Briefcase, iconTint = NavigatorColors.TextSecondary)
                NvButton("Недавние", {}, kind = NvButtonKind.Secondary, icon = NvIcon.ClockRotateLeft, iconTint = NavigatorColors.TextSecondary)
            }
            SectionCaption(
                "История, хранится локально",
                Modifier.padding(start = 16.dp, end = 16.dp, top = 12.dp, bottom = 4.dp),
            )
            CardGroup(Modifier.padding(horizontal = 16.dp)) {
                NvListItem("Тверская ул., 15", "Москва, 2,4 км", {}, leadingIcon = NvIcon.ClockRotateLeft)
                NvDivider()
                NvListItem("Парк Горького, главный вход", "Москва, 5,1 км", {}, leadingIcon = NvIcon.ClockRotateLeft)
            }
            SectionCaption(
                "Результаты, API обновлено 14:32",
                Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp),
            )
            CardGroup(Modifier.padding(horizontal = 16.dp)) {
                NvListItem("Тверская улица", "Москва, 1,1 км", onResult, leadingIcon = NvIcon.LocationDot, trailingIcon = NvIcon.ChevronRight)
                NvDivider()
                NvListItem("Тверская площадь", "Москва, 1,4 км", onResult, leadingIcon = NvIcon.LocationDot, trailingIcon = NvIcon.ChevronRight)
                NvDivider()
                NvListItem("Тверской бульвар", "Москва, 1,8 км", onResult, leadingIcon = NvIcon.LocationDot, trailingIcon = NvIcon.ChevronRight)
            }
        }
    }
}

// ═══ 4. Карточка точки (screen.point) ═══

@Composable
fun PointScreen(
    onBack: () -> Unit,
    onRouteHere: () -> Unit,
    onRouteFrom: () -> Unit,
    onRecalc: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenFrame(NavigatorColors.MapCanvas, modifier) {
        NvMapBase {
            PositionMarker(166, 240)
            DestinationPin(182, 330, width = 30, height = 40)
        }
        DesignStatusBar(time = "14:32", light = true)
        NvIconButton(
            NvIcon.ArrowLeft,
            NavigatorColors.TextPrimary,
            onBack,
            modifier = Modifier.offset(16.dp, 72.dp),
        )
        NvTag("LIVE", tone = NvTagTone.Live, modifier = Modifier.offset(330.dp, 80.dp))
        Box(Modifier.align(Alignment.BottomStart)) {
            SheetColumn(height = 434) {
                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    BasicText("Тверская ул., 15", style = NavigatorType.Title)
                    BasicText(
                        "55.7602, 37.6185, reverse-geocoding, обновлено 14:32",
                        style = NavigatorType.Caption,
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NvTag("LIVE", tone = NvTagTone.Live)
                    NvTag("ДТП в 400 м", tone = NvTagTone.Warning)
                    NvTag("Не сохранено", tone = NvTagTone.Unknown, dotVisible = false)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(NavigatorColors.StatusWarningBg)
                        .padding(vertical = 10.dp, horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    NvGlyph(NvIcon.CarBurst, NavigatorColors.StatusWarning, Modifier.size(18.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                        BasicText("ДТП, занята правая полоса", style = NavigatorType.Strong.copy(fontSize = 14.sp))
                        BasicText("Действует до 15:30, достоверность 87%", style = NavigatorType.Caption)
                    }
                }
                NvButton(
                    label = "Сюда",
                    onClick = onRouteHere,
                    modifier = Modifier.fillMaxWidth(),
                    size = NvButtonSize.Large,
                    icon = NvIcon.LocationArrow,
                )
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        NvButton("Отсюда", onRouteFrom, Modifier.weight(1f).height(40.dp), kind = NvButtonKind.Secondary)
                        NvButton("Сохранить", {}, Modifier.weight(1f).height(40.dp), kind = NvButtonKind.Secondary)
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        NvButton("На карте", {}, Modifier.weight(1f).height(40.dp), kind = NvButtonKind.Secondary)
                        NvButton("Пересчитать", onRecalc, Modifier.weight(1f).height(40.dp), kind = NvButtonKind.Secondary)
                    }
                }
            }
        }
    }
}
