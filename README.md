# Navigator

Кроссплатформенный навигатор на Kotlin Multiplatform: Android, iOS, Desktop (JVM) и Web. Клиентский KMP-монорепозиторий для автомобильной навигации с учётом дорожной обстановки; взаимодействует с внешним API навигатора через стабильные DTO и репозитории.

**Стек:** Kotlin Multiplatform, Compose Multiplatform, Decompose, Ktor Client, kotlinx.serialization, kotlinx.coroutines/Flow, DataStore KMP, MapLibre Compose.

**Статус:** на стадии архитектурного плана. Детали — в [Плане структуры навигатора KMP](План_структуры_навигатора_KMP.md), схемы — в `navigator_mvp_assets/`.

## Ветки
- `main` — стабильная ветка.
- `dev` — рабочая ветка; изменения попадают в `main` через merge/PR.
