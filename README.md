# Navigator

Кроссплатформенный навигатор на Kotlin Multiplatform: Android, iOS, Desktop (JVM) и Web. Клиентский KMP-монорепозиторий для автомобильной навигации с учётом дорожной обстановки; взаимодействует с внешним API навигатора через стабильные DTO и репозитории.

**Стек:** Kotlin Multiplatform, Compose Multiplatform, Decompose, kotlinx.serialization. По плану также Ktor Client, kotlinx.coroutines/Flow, DataStore KMP и MapLibre Compose — эти слои ещё не подключены (см. «Статус»).

**Версии:** Kotlin 2.4.21, Compose Multiplatform 1.12.2, AGP 9.5.1, Decompose 3.5.0, Gradle 9.9. Android: minSdk 26, target/compileSdk 36.

## Структура

| Модуль | Назначение |
|---|---|
| `core/model` | DTO внешнего API: `Route`, `Maneuver`, `TrafficSegment`, `RoadEvent`, `DataSourceStatus` и др. |
| `app/shared/ui` | Дизайн-система из KUI-токенов и 12 экранов макета (Welcome, Map, Search, Point, Build, Preview, Guidance, Arrival, Event, Settings, Status, About) |
| `app/shared/navigation` | Decompose-компоненты, стек экранов, `NavigatorApp` |
| `app/androidApp` | Android-приложение (Compose + `@Preview` всех экранов) |
| `app/desktopApp` | Compose Desktop-приложение |
| `app/webApp` | Kotlin/JS + Compose Web |
| `.kimi-design` | Синхронизированный исходник дизайн-системы (KUI: токены, компоненты, страницы) |

## Запуск

```bash
# Android (APK: app/androidApp/build/outputs/apk/debug/)
./gradlew :app:androidApp:assembleDebug
adb install -r app/androidApp/build/outputs/apk/debug/androidApp-debug.apk

# Desktop
./gradlew :app:desktopApp:run

# Web (http://localhost:8080)
./gradlew :app:webApp:jsBrowserDevelopmentRun

# Проверка общей компиляции
./gradlew :app:androidApp:assembleDebug :app:desktopApp:compileKotlinJvm
```

Для сборки нужен Android SDK и JDK; Gradle сам скачивает daemon JVM 25 (см. `gradle/gradle-daemon-jvm.properties`). `local.properties` с `sdk.dir` создаётся локально и не коммитится.

**Статус:** UI-прототип по дизайн-макету — все 12 экранов, компоненты дизайн-системы, навигация на Decompose, доменные DTO. Пока не реализованы: сетевой слой и репозитории (Ktor), DataStore, MapLibre-карта (сейчас схематичный `NvMapBase` на Canvas), геолокация, голосовые подсказки и отдельный iOS entry point (таргеты `iosArm64`/`iosSimulatorArm64` объявлены в shared-модулях). Детали — в [Плане структуры навигатора KMP](План_структуры_навигатора_KMP.md), схемы — в `navigator_mvp_assets/`.

## Ветки
- `main` — стабильная ветка.
- `dev` — рабочая ветка; изменения попадают в `main` через merge/PR.
