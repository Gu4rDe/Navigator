package ru.navigator.shared.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.navigator.shared.ui.components.DesignStatusBar
import ru.navigator.shared.ui.components.NvAlert
import ru.navigator.shared.ui.components.NvButton
import ru.navigator.shared.ui.components.NvButtonKind
import ru.navigator.shared.ui.components.NvButtonSize
import ru.navigator.shared.ui.components.NvDivider
import ru.navigator.shared.ui.components.NvGlyph
import ru.navigator.shared.ui.components.NvIcon
import ru.navigator.shared.ui.components.NvIconButton
import ru.navigator.shared.ui.components.NvSegmented
import ru.navigator.shared.ui.components.NvSettingRow
import ru.navigator.shared.ui.components.NvSwitch
import ru.navigator.shared.ui.components.NvTag
import ru.navigator.shared.ui.components.NvTagTone
import ru.navigator.shared.ui.theme.NavigatorColors
import ru.navigator.shared.ui.theme.NavigatorType
import ru.navigator.shared.ui.theme.PillShape

// ═══ 9. Событие ИТС (screen.event) ═══

@Composable
fun EventScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenFrame(NavigatorColors.SurfaceBase, modifier) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            DesignStatusBar(time = "14:32", light = false)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NvIconButton(NvIcon.ArrowLeft, NavigatorColors.TextPrimary, onBack, modifier = Modifier.size(44.dp))
                BasicText("Событие ИТС", style = NavigatorType.Subtitle, modifier = Modifier.weight(1f))
                NvTag("Активно", tone = NvTagTone.Success)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavigatorColors.SurfaceCard)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    Modifier.size(52.dp).background(NavigatorColors.StatusErrorBg, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    NvGlyph(NvIcon.CarBurst, NavigatorColors.StatusError, Modifier.size(24.dp))
                }
                Column(
                    modifier = Modifier.width(150.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    BasicText("ДТП", style = NavigatorType.Title)
                    BasicText("Тверская ул., 300 м впереди по маршруту", style = NavigatorType.Caption)
                }
                Spacer(Modifier.weight(1f))
                NvTag("Серьёзное", tone = NvTagTone.Error, dotVisible = false)
            }
            CardGroup(Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp)) {
                NvSettingRow(
                    "Срок действия",
                    "с 14:10 до 15:30, истекает через 58 мин",
                    control = { NvGlyph(NvIcon.Clock, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                )
                NvDivider()
                NvSettingRow(
                    "Обновлено",
                    "14:32, источник ИТС, поток LIVE",
                    control = { NvTag("LIVE", tone = NvTagTone.Live) },
                )
                NvDivider()
                Column(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 13.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        BasicText("Достоверность", style = NavigatorType.Body)
                        BasicText("87%", style = NavigatorType.Strong)
                    }
                    Box(
                        Modifier.fillMaxWidth().height(6.dp).background(NavigatorColors.DividerSubtle, PillShape),
                    ) {
                        Box(Modifier.width(284.dp).height(6.dp).background(NavigatorColors.StatusSuccess, PillShape))
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavigatorColors.SurfaceCard)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                BasicText("Описание", style = NavigatorType.Caption)
                BasicText(
                    "Столкновение двух автомобилей, занята правая полоса. Объезд по левой полосе, скорость потока 12 км/ч.",
                    style = NavigatorType.Body,
                )
            }
            Spacer(Modifier.height(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 16.dp, end = 16.dp, bottom = 40.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                NvButton("Пересчитать маршрут", {}, Modifier.fillMaxWidth(), size = NvButtonSize.Large)
                NvButton("Показать на карте", onBack, Modifier.fillMaxWidth(), kind = NvButtonKind.Secondary)
            }
        }
    }
}

// ═══ 10. Настройки (screen.settings) ═══

@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenFrame(NavigatorColors.SurfaceBase, modifier) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            DesignStatusBar(time = "14:32", light = false)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NvIconButton(NvIcon.ArrowLeft, NavigatorColors.TextPrimary, onBack, modifier = Modifier.size(44.dp))
                BasicText("Настройки", style = NavigatorType.Subtitle, modifier = Modifier.weight(1f))
            }
            SectionCaption("Голос и язык", Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp))
            CardGroup(Modifier.padding(horizontal = 16.dp)) {
                NvSettingRow(
                    "Язык",
                    "Русский",
                    control = { NvGlyph(NvIcon.ChevronRight, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                )
                NvDivider()
                NvSettingRow(
                    "Голос подсказок",
                    "Алёна, женский",
                    control = { NvGlyph(NvIcon.ChevronRight, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                )
            }
            SectionCaption("Навигация", Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp))
            CardGroup(Modifier.padding(horizontal = 16.dp)) {
                NvSettingRow(
                    "Единицы измерения",
                    "Километры",
                    control = { NvGlyph(NvIcon.ChevronRight, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                )
                NvDivider()
                NvSettingRow(
                    "Авто-пересчёт маршрута",
                    "При сходе или изменении трафика",
                    control = { NvSwitch(checked = true, onCheckedChange = {}) },
                )
                NvDivider()
                NvSettingRow(
                    "Порог предупреждений",
                    "За 300 м до манёвра",
                    control = { NvGlyph(NvIcon.ChevronRight, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                )
            }
            SectionCaption("Карта и данные", Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 4.dp))
            CardGroup(Modifier.padding(horizontal = 16.dp)) {
                NvSettingRow(
                    "Режим карты",
                    "Авто, день и ночь",
                    control = { NvGlyph(NvIcon.ChevronRight, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                )
                NvDivider()
                NvSettingRow(
                    "Режим симуляции",
                    "Для Desktop и Web вместо GNSS",
                    control = { NvSwitch(checked = false, onCheckedChange = {}) },
                )
                NvDivider()
                NvSettingRow(
                    title = "Сбросить локальные данные",
                    subtitle = "История, сохранённые точки, DataStore",
                    titleColor = NavigatorColors.StatusError,
                    control = { NvGlyph(NvIcon.TrashCan, NavigatorColors.StatusError, Modifier.size(16.dp)) },
                )
            }
            BasicText(
                "Настройки хранятся локально, DataStore",
                style = NavigatorType.Caption.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 24.dp, end = 24.dp),
            )
        }
    }
}

// ═══ 11. Статус и симуляция (screen.status) ═══

@Composable
fun StatusScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenFrame(NavigatorColors.SurfaceBase, modifier) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            DesignStatusBar(time = "14:32", light = false)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NvIconButton(NvIcon.ArrowLeft, NavigatorColors.TextPrimary, onBack, modifier = Modifier.size(44.dp))
                BasicText("Статус и симуляция", style = NavigatorType.Subtitle, modifier = Modifier.weight(1f))
                NvTag("LIVE", tone = NvTagTone.Live)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavigatorColors.SurfaceCard),
            ) {
                BasicText(
                    "Потоки данных",
                    style = NavigatorType.Strong,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
                )
                NvSettingRow(
                    "Маршруты",
                    "обновлено 14:32:10, 12 с назад",
                    control = { NvTag("OK", tone = NvTagTone.Success, dotVisible = false) },
                )
                NvDivider()
                NvSettingRow(
                    "Пробки",
                    "обновлено 14:31:58, 24 с назад",
                    control = { NvTag("OK", tone = NvTagTone.Success, dotVisible = false) },
                )
                NvDivider()
                NvSettingRow(
                    "События",
                    "данные на 14:20, задержка потока",
                    control = { NvTag("Stale", tone = NvTagTone.Warning, dotVisible = false) },
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavigatorColors.SurfaceCard)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    BasicText("Покрытие картой", style = NavigatorType.Strong)
                    BasicText("82%", style = NavigatorType.Strong)
                }
                Box(Modifier.fillMaxWidth().height(6.dp).background(NavigatorColors.DividerSubtle, PillShape)) {
                    Box(Modifier.width(268.dp).height(6.dp).background(NavigatorColors.BrandPrimary, PillShape))
                }
                BasicText(
                    "Районы Север и Восток без данных показаны серым, а не зелёным",
                    style = NavigatorType.Caption,
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavigatorColors.SurfaceCard)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NvGlyph(NvIcon.CircleCheck, NavigatorColors.StatusSuccess, Modifier.size(18.dp))
                Column(
                    modifier = Modifier.width(280.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    BasicText("Ошибок нет", style = NavigatorType.Strong)
                    BasicText("За последний час, API отвечает стабильно", style = NavigatorType.Caption)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavigatorColors.SurfaceCard)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                BasicText("Панель симуляции", style = NavigatorType.Strong)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    NvIconButton(NvIcon.Play, NavigatorColors.TextInverse, {}, kind = ru.navigator.shared.ui.components.NvIconButtonKind.Primary)
                    NvIconButton(NvIcon.Pause, NavigatorColors.TextSecondary, {}, elevated = false)
                    NvSegmented(listOf("1×", "2×", "4×"), selectedIndex = 0, onSelect = {})
                }
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Box(Modifier.fillMaxWidth().height(6.dp).background(NavigatorColors.DividerSubtle, PillShape)) {
                        Box(Modifier.width(130.dp).height(6.dp).background(NavigatorColors.BrandPrimary, PillShape))
                    }
                    BasicText("00:12:40 / 00:31:00", style = NavigatorType.Caption)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NvButton("Off-route", {}, kind = NvButtonKind.Secondary)
                    NvButton("Stale", {}, kind = NvButtonKind.Secondary)
                    NvButton("ДТП", {}, kind = NvButtonKind.Secondary)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NvButton("Offline", {}, kind = NvButtonKind.Secondary)
                    NvButton("Прибытие", {}, kind = NvButtonKind.Secondary)
                    NvButton("Нет покрытия", {}, kind = NvButtonKind.Secondary)
                }
            }
        }
    }
}

// ═══ 12. О программе (screen.about) ═══

@Composable
fun AboutScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ScreenFrame(NavigatorColors.SurfaceBase, modifier) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            DesignStatusBar(time = "14:32", light = false)
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NvIconButton(NvIcon.ArrowLeft, NavigatorColors.TextPrimary, onBack, modifier = Modifier.size(44.dp))
                BasicText("О программе", style = NavigatorType.Subtitle, modifier = Modifier.weight(1f))
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp, start = 24.dp, end = 24.dp, bottom = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Box(
                    Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(NavigatorColors.BrandPrimary),
                    contentAlignment = Alignment.Center,
                ) {
                    NvGlyph(NvIcon.Route, NavigatorColors.TextInverse, Modifier.size(32.dp))
                }
                BasicText("Навигатор", style = NavigatorType.Title)
                BasicText("Версия 1.4.2 (104), Compose Multiplatform", style = NavigatorType.Caption)
                NvTag("Режим данных: LIVE", tone = NvTagTone.Live, dotVisible = false)
            }
            CardGroup(Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
                NvSettingRow(
                    "Данные карты",
                    "© OpenStreetMap contributors",
                    control = { NvGlyph(NvIcon.ChevronRight, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                )
                NvDivider()
                NvSettingRow(
                    "Лицензии картографии",
                    "ODbL 1.0, CC BY-SA 2.0",
                    control = { NvGlyph(NvIcon.ChevronRight, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                )
                NvDivider()
                NvSettingRow(
                    "Правовая информация",
                    "Условия использования и приватность",
                    control = { NvGlyph(NvIcon.ChevronRight, NavigatorColors.TextDisabled, Modifier.size(14.dp)) },
                )
            }
            NvAlert(
                title = "Работает без сети",
                message = "Экран доступен полностью offline, все тексты встроены в приложение",
                modifier = Modifier.padding(top = 12.dp, start = 16.dp, end = 16.dp),
            )
            Spacer(Modifier.weight(1f))
            BasicText(
                "© 2025 Навигатор",
                style = NavigatorType.Caption.copy(textAlign = TextAlign.Center),
                modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
            )
        }
    }
}
