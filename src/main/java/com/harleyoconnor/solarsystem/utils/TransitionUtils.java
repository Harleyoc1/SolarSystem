package com.harleyoconnor.solarsystem.utils;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * @author Harley O'Connor
 */
public final class TransitionUtils {

    public static RotateTransition setupRotationTransition(final Node node, final double duration, final Point3D axis, final int angle, final int cycleCount, final Interpolator interpolator) {
        final RotateTransition rotation = new RotateTransition();

        rotation.setNode(node);
        rotation.setDuration(Duration.millis(duration));
        rotation.setAxis(axis);
        rotation.setByAngle(angle);
        rotation.setCycleCount(cycleCount);
        rotation.setInterpolator(interpolator);

        return rotation;
    }

}
