package com.harleyoconnor.solarsystem.util;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * @author Harley O'Connor
 */
public final class TransitionUtils {

    /**
     * Sets up a rotate transition for the given node.
     *
     * @param node The node to add the transition to.
     * @param durationMillis The duration in milliseconds for one full rotation cycle.
     * @param axis The axis with which to rotate on.
     * @param angle The angle that it should rotate to.
     * @param cycleCount The amount of cycles to do.
     * @param interpolator The interpolator.
     * @return The rotate transition object.
     */
    public static RotateTransition setupRotationTransition(final Node node, final double durationMillis, final Point3D axis, final int angle, final int cycleCount, final Interpolator interpolator) {
        final RotateTransition rotation = new RotateTransition(Duration.millis(durationMillis), node);

        rotation.setAxis(axis);
        rotation.setByAngle(angle);
        rotation.setCycleCount(cycleCount);
        rotation.setInterpolator(interpolator);

        return rotation;
    }

}
