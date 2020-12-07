package com.harleyoconnor.solarsystem.movable.moons;

import com.harleyoconnor.solarsystem.movable.IRotatingObject;
import com.harleyoconnor.solarsystem.movable.planets.Planet;
import com.harleyoconnor.solarsystem.util.TransitionUtils;
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
public abstract class Moon implements IRotatingObject {

    private final int radius;

    protected final StackPane moonContainer = new StackPane();
    protected final StackPane parentPane;
    protected final Planet parentPlanet;

    protected Sphere moonSphere;
    protected RotateTransition moonRotation;
    protected Rotate rotationAroundPlanet;

    private double angle = 0;

    public Moon (final int radius, final StackPane parentPane, final Planet parentPlanet) {
        this.radius = radius;
        this.parentPane = parentPane;
        this.parentPlanet = parentPlanet;
    }

    @Override
    public void initModel() {
        this.moonSphere = new Sphere(this.radius);

        final Image planetTexture = this.getTexture();
        final PhongMaterial planetMaterial = new PhongMaterial();
        planetMaterial.setDiffuseMap(planetTexture);

        this.moonSphere.setMaterial(planetMaterial);

        this.moonContainer.getChildren().add(this.moonSphere);
    }

    @Override
    public void initRotations(double centreX, double centreY) {
        this.moonRotation = TransitionUtils.setupRotationTransition(this.moonSphere, 5000, Rotate.Y_AXIS, -360, Animation.INDEFINITE, Interpolator.LINEAR);
        this.moonRotation.play();

        this.rotationAroundPlanet = new Rotate(0, centreX + 100, centreY); // Replace with call to planet position.
        this.moonContainer.getTransforms().addAll(this.parentPlanet.getRotationAroundStar(), this.rotationAroundPlanet);

        // When screen is resized, change pivot coordinates of earth rotation.
        this.parentPane.widthProperty().addListener((observable, oldValue, newValue) ->
                this.rotationAroundPlanet.setPivotX(this.parentPlanet.getPositionX()));
        this.parentPane.heightProperty().addListener((observable, oldValue, newValue) ->
                this.rotationAroundPlanet.setPivotY(this.parentPlanet.getPositionY()));
    }

    @Override
    public void onTick (final ActionEvent event) {
        this.rotationAroundPlanet.setAngle(this.angle);
        this.angle += 0.5;
        if (this.angle > 360) this.angle = 0;
    }

    public abstract Image getTexture ();

    public StackPane getMoonContainer() {
        return moonContainer;
    }

}
