package ru.navigator.core.model

/** Координата WGS84. */
data class GeoPoint(val lat: Double, val lon: Double)

/** Манёвр маршрута (контракт внешнего API `/v1`). */
data class Maneuver(
    val type: String,
    val location: GeoPoint,
    val instruction: String,
    val distanceToNextMeters: Double,
)

/** Маршрут с геометрией, манёврами и свежестью дорожных данных. */
data class Route(
    val id: String,
    val geometry: List<GeoPoint>,
    val maneuvers: List<Maneuver>,
    val distanceMeters: Double,
    val durationSeconds: Double,
    /** Время выборки пробочных данных, null — неизвестное. */
    val trafficSampledAt: String? = null,
)

/** Дорожный сегмент: скорость и покрытие; null-поля отображаются как unknown. */
data class TrafficSegment(
    val segmentId: String,
    val speedKph: Double? = null,
    val sampledAt: String? = null,
    val expiresAt: String? = null,
    val coverage: String,
)

/** Событие ИТС (ДТП, ремонт и т.п.). */
data class RoadEvent(
    val id: String,
    val type: String,
    val geometry: List<GeoPoint>,
    val severity: String,
    val validFrom: String,
    val validTo: String? = null,
    val description: String? = null,
)

/** Статус источника данных: последний успех, свежесть и безопасное сообщение об ошибке. */
data class DataSourceStatus(
    val lastSuccessAt: String? = null,
    val freshness: String,
    val coverageRatio: Double? = null,
    val safeErrorMessage: String? = null,
)
