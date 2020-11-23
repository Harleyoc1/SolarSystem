package com.harleyoconnor.solarsystem;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.javautilities.IntegerUtils;
import com.harleyoconnor.solarsystem.planets.Earth;
import com.harleyoconnor.solarsystem.planets.Planet;
import com.harleyoconnor.solarsystem.utils.TransitionUtils;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SolarSystem extends Application {

    final String[] colours = {"a37374", "7a8dab", "ffffff", "a45c3e"};

    public static final String FILE_PREFIX = "file:";

    @Override
    public void start (Stage primaryStage) {
        final StackPane root = new StackPane();

        final Scene scene = new Scene(root, 1500, 900);

        primaryStage.setHeight(900); primaryStage.setWidth(1500);

        final double initialCentreX = primaryStage.getWidth() / 2;
        final double initialCentreY = primaryStage.getHeight() / 2;

        final Planet earth = new Earth (root);

        earth.initModel();
        earth.setupRotations(initialCentreX - 250, initialCentreY);

        final Sphere sun = new Sphere(150);
        sun.setTranslateX(-250);

        final Image sunImage = new Image(FILE_PREFIX + FileUtils.getInternalPath("textures/stars/sun.jpg"));
        final PhongMaterial sunPhong = new PhongMaterial();
        sunPhong.setDiffuseMap(sunImage);
        sun.setMaterial(sunPhong);

        // Setup Sun rotation.
        final RotateTransition sunRotation = TransitionUtils.setupRotationTransition(sun, 5000, Rotate.Y_AXIS, -360, Animation.INDEFINITE, Interpolator.LINEAR);
        sunRotation.play();

        this.createStars(root);

        // Add Earth and Sun to root.
        earth.addToParent();
        root.getChildren().addAll(sun);

        root.getStylesheets().add("file:" + FileUtils.getFile("stylesheets/default.css").getPath());

        primaryStage.setScene(scene);
        primaryStage.show();

        final Timeline tick = new Timeline(60, new KeyFrame(new Duration(10), earth::onTick));

        tick.setCycleCount(-1);
        tick.play();

    }

    private void createStars(final StackPane root) {
        for (int i = 0; i < IntegerUtils.getRandomIntBetween(5000, 5500); i++) {
            final Circle star = new Circle(IntegerUtils.getRandomIntBetween(1, 50) == 1 ? 3 : IntegerUtils.getRandomIntBetween(1, 9) == 1 ? 2 : 1);
            star.setFill(Paint.valueOf("#" + this.colours[IntegerUtils.getRandomIntBetween(0, this.colours.length - 1)]));

            star.setTranslateX(IntegerUtils.getRandomIntBetween(-1200, 1200));
            star.setTranslateY(IntegerUtils.getRandomIntBetween(-1200, 1200));

            root.getChildren().add(star);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
