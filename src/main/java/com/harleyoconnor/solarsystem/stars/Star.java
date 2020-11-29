package com.harleyoconnor.solarsystem.stars;

import com.harleyoconnor.solarsystem.IRotatingSpaceObject;
import com.harleyoconnor.solarsystem.utils.TransitionUtils;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

/**
 * @author Harley O'Connor
 */
public abstract class Star implements IRotatingSpaceObject {

    private final int radius;
    private final double positionX;
    private final double positionY;

    protected final StackPane parentPane;

    protected Sphere starSphere;
    protected RotateTransition starRotation;

    public Star (final int radius, final StackPane parentPane, final double positionX, final double positionY) {
        this.radius = radius;
        this.parentPane = parentPane;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    @Override
    public void initModel() {
        this.starSphere = new Sphere(this.radius);

        final Image planetTexture = this.getTexture();
        final PhongMaterial planetMaterial = new PhongMaterial();
        planetMaterial.setDiffuseMap(planetTexture);

        this.starSphere.setMaterial(planetMaterial);

        this.starSphere.translateXProperty().set(this.positionX);
        this.starSphere.translateYProperty().set(this.positionY);
    }

    @Override
    public void initRotations(final double centreX, final double centreY) {
        this.starRotation = TransitionUtils.setupRotationTransition(this.starSphere, 5000, Rotate.Y_AXIS, -360, Animation.INDEFINITE, Interpolator.LINEAR);
        this.starRotation.play();
    }

    @Override
    public void onTick(ActionEvent event) {

    }

    public abstract Image getTexture ();

    public Sphere getStarSphere() {
        return starSphere;
    }

    public double getPositionX() {
        return (this.parentPane.getWidth() / 2) - this.positionX;
    }

    public double getPositionY() {
        return (this.parentPane.getHeight() / 2) - this.positionY;
    }

}
