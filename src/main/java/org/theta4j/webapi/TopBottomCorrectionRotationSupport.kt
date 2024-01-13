package org.theta4j.webapi

import kotlinx.serialization.Serializable

@Serializable
data class TopBottomCorrectionRotationSupport(
        val pitch: PitchSupport,
        val roll: RollSupport,
        val yaw: YawSupport,
) {
    @Serializable
    data class PitchSupport(
            val minPitch: Double,
            val maxPitch: Double,
            val stepSize: Double,
    )

    @Serializable
    data class RollSupport(
            val minRoll: Double,
            val maxRoll: Double,
            val stepSize: Double,
    )

    @Serializable
    data class YawSupport(
            val minYaw: Double,
            val maxYaw: Double,
            val stepSize: Double,
    )
}
