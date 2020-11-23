package com.harleyoconnor.solarsystem;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.javautilities.IntegerUtils;
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

    private static final String FILE_PREFIX = "file:";
    private static Rotate earthRotationAboutSun;

    private double earthAngle = 0;
    
    @Override
    public void start (Stage primaryStage) {
        final StackPane root = new StackPane();

        final Scene scene = new Scene(root, 1500, 900);

        primaryStage.setHeight(900); primaryStage.setWidth(1500);

        final double xCentre = primaryStage.getWidth() / 2;
        final double yCentre = primaryStage.getHeight() / 2;

        final StackPane earthContainer = new StackPane();

        // This sets up the earth.
        final Sphere earth = new Sphere(50);

        final Sphere sun = new Sphere(150);
        sun.setTranslateX(-250);

        // This sets up the image of the earth to wrap around my earth.
        final Image earthImage = new Image(FILE_PREFIX + FileUtils.getInternalPath("earth.jpg"));
        final PhongMaterial earthPhong = new PhongMaterial();
        earthPhong.setDiffuseMap(earthImage);
        earth.setMaterial(earthPhong);

        final Image sunImage = new Image(FILE_PREFIX + FileUtils.getInternalPath("sun.jpeg"));
        final PhongMaterial sunPhong = new PhongMaterial();
        sunPhong.setDiffuseMap(sunImage);
        sun.setMaterial(sunPhong);
        
        // Setup Earth rotation.
        final RotateTransition earthRotation = TransitionUtils.setupRotationTransition(earth, 5000, Rotate.Y_AXIS, -360, Animation.INDEFINITE, Interpolator.LINEAR);
        earthRotation.play();

        // Setup Sun rotation.
        final RotateTransition sunRotation = TransitionUtils.setupRotationTransition(sun, 5000, Rotate.Y_AXIS, -360, Animation.INDEFINITE, Interpolator.LINEAR);
        sunRotation.play();

        this.createStars(root);

        earth.setTranslateZ(1);

        System.out.println(xCentre);
        System.out.println(yCentre);

        earthRotationAboutSun = new Rotate(0, xCentre - 250, yCentre);

        earthContainer.getTransforms().add(earthRotationAboutSun);

        earthContainer.getChildren().add(earth);

        // Add Earth and Sun to root.
        root.getChildren().addAll(earthContainer, sun);

        root.getStylesheets().add("file:" + FileUtils.getFile("stylesheets/default.css").getPath());

        // primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        final Timeline tick = new Timeline(60, new KeyFrame(new Duration(10), this::earthTick));

        tick.setCycleCount(-1);
        tick.play();

        // When screen is resized, change pivot coordinates of earth rotation.
        root.widthProperty().addListener((observable, oldValue, newValue) ->
                earthRotationAboutSun.setPivotX((root.getWidth() / 2) - 250));
        root.heightProperty().addListener((observable, oldValue, newValue) ->
                earthRotationAboutSun.setPivotY(root.getHeight() / 2));
    }

    private void earthTick (final ActionEvent actionEvent) {
        earthRotationAboutSun.setAngle(this.earthAngle);
        this.earthAngle += 0.5;
        if (this.earthAngle > 360) this.earthAngle = 0;
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
