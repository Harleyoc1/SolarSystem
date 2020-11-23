package com.harleyoconnor.solarsystem.planets;

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
public abstract class Planet {

    private final int radius;

    protected final StackPane planetContainer;
    protected final StackPane parentPane;

    protected Sphere planetSphere;
    protected RotateTransition planetRotation;
    protected Rotate rotationAroundStar;

    private double angle;

    public Planet (final int radius, final StackPane parentPane) {
        this.radius = radius;
        this.parentPane = parentPane;
        this.planetContainer = new StackPane();
    }

    public void initModel () {
        this.planetSphere = new Sphere(this.radius);

        final Image planetTexture = this.getTexture();
        final PhongMaterial planetMaterial = new PhongMaterial();
        planetMaterial.setDiffuseMap(planetTexture);

        this.planetSphere.setMaterial(planetMaterial);

        this.planetContainer.getChildren().add(this.planetSphere);
    }

    public void setupRotations (final double centreX, final double centreY) {
        this.planetRotation = TransitionUtils.setupRotationTransition(this.planetSphere, 5000, Rotate.Y_AXIS, -360, Animation.INDEFINITE, Interpolator.LINEAR);
        this.planetRotation.play();

        this.rotationAroundStar = new Rotate(0, centreX, centreY); // Replace with call to star position.
        this.planetContainer.getTransforms().add(this.rotationAroundStar);

        // When screen is resized, change pivot coordinates of earth rotation.
        this.parentPane.widthProperty().addListener((observable, oldValue, newValue) ->
                this.rotationAroundStar.setPivotX((this.parentPane.getWidth() / 2) - 250));
        this.parentPane.heightProperty().addListener((observable, oldValue, newValue) ->
                this.rotationAroundStar.setPivotY(this.parentPane.getHeight() / 2));
    }

    public void addToParent () {
        this.parentPane.getChildren().add(this.planetContainer);
    }

    public void onTick (final ActionEvent event) {
        this.rotationAroundStar.setAngle(this.angle);
        this.angle += 0.25;
        if (this.angle > 360) this.angle = 0;
    }

    public abstract Image getTexture ();

}
