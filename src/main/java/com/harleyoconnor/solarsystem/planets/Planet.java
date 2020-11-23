package com.harleyoconnor.solarsystem.planets;

import com.harleyoconnor.solarsystem.IRotatingModel;
import com.harleyoconnor.solarsystem.ISpaceObject;
import com.harleyoconnor.solarsystem.stars.Star;
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
public abstract class Planet implements ISpaceObject, IRotatingModel {

    private final int radius;

    protected final StackPane planetContainer;
    protected final StackPane parentPane;
    protected final Star parentStar;

    protected Sphere planetSphere;
    protected RotateTransition planetRotation;
    protected Rotate rotationAroundStar;

    private double angle;

    public Planet (final int radius, final StackPane parentPane, final Star parentStar) {
        this.radius = radius;
        this.parentPane = parentPane;
        this.parentStar = parentStar;
        this.planetContainer = new StackPane();
    }

    @Override
    public void initModel () {
        this.planetSphere = new Sphere(this.radius);

        final Image planetTexture = this.getTexture();
        final PhongMaterial planetMaterial = new PhongMaterial();
        planetMaterial.setDiffuseMap(planetTexture);

        this.planetSphere.setMaterial(planetMaterial);

        this.planetContainer.getChildren().add(this.planetSphere);
    }

    @Override
    public void setupRotations (final double centreX, final double centreY) {
        this.planetRotation = TransitionUtils.setupRotationTransition(this.planetSphere, 5000, Rotate.Y_AXIS, -360, Animation.INDEFINITE, Interpolator.LINEAR);
        this.planetRotation.play();

        this.rotationAroundStar = new Rotate(0, centreX, centreY); // Replace with call to star position.
        this.planetContainer.getTransforms().add(this.rotationAroundStar);

        // When screen is resized, change pivot coordinates of earth rotation.
        this.parentPane.widthProperty().addListener((observable, oldValue, newValue) ->
                this.rotationAroundStar.setPivotX(this.parentStar.getPositionX()));
        this.parentPane.heightProperty().addListener((observable, oldValue, newValue) ->
                this.rotationAroundStar.setPivotY(this.parentStar.getPositionY()));
    }

    @Override
    public void onTick (final ActionEvent event) {
        this.rotationAroundStar.setAngle(this.angle);
        this.angle += 0.25;
        if (this.angle > 360) this.angle = 0;
    }

    public abstract Image getTexture ();

    public StackPane getPlanetContainer() {
        return planetContainer;
    }

}
