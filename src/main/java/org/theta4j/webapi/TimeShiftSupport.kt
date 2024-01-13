package org.theta4j.webapi

import kotlinx.serialization.Serializable

@Serializable
data class TimeShiftSupport(
        val firstShooting: List<TimeShift.ShootingOrder>,
        val firstInterval: IntervalSupport,
        val secondInterval: IntervalSupport,
) {
    @Serializable
    data class IntervalSupport(
            val minInterval: Int,
            val maxInterval: Int,
            val stepSize: Int,
    )
}
