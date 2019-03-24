package org.theta4j.webapi;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Options for time-shift shooting.
 */
public final class TimeShift {
    /**
     * Shooting order.
     */
    public enum ShootingOrder {
        /**
         * Shoot from rear side (side with monitor) after shooting from front side (side with logo)
         */
        @SerializedName("front") FRONT,

        /**
         * Shoot from front side after shooting from rear side.
         */
        @SerializedName("rear") REAR
    }

    private final ShootingOrder shootingOrder;

    private final int firstInterval;

    private final int secondInterval;

    /**
     * Shooting order.
     */
    public ShootingOrder getShootingOrder() {
        return shootingOrder;
    }

    /**
     * Time (sec) before 1st lens shooting.
     */
    public int getFirstInterval() {
        return firstInterval;
    }

    /**
     * Time (sec) from 1st lens shooting until start of 2nd lens shooting.
     */
    public int getSecondInterval() {
        return secondInterval;
    }

    /**
     * @param shootingOrder  Shooting order.
     * @param firstInterval  Time (sec) before 1st lens shooting.
     * @param secondInterval Time (sec) from 1st lens shooting until start of 2nd lens shooting.
     * @throws NullPointerException if shootingOrder is null.
     */
    public TimeShift(@Nonnull ShootingOrder shootingOrder, int firstInterval, int secondInterval) {
        this.shootingOrder = Objects.requireNonNull(shootingOrder, "shootingOrder can not be null.");
        this.firstInterval = firstInterval;
        this.secondInterval = secondInterval;
    }
}
