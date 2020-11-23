package com.harleyoconnor.solarsystem;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.javautilities.IntegerUtils;
import com.harleyoconnor.solarsystem.planets.Earth;
import com.harleyoconnor.solarsystem.planets.Planet;
import com.harleyoconnor.solarsystem.stars.Star;
import com.harleyoconnor.solarsystem.stars.Sun;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SolarSystem extends Application {

    final String[] colours = {"a37374", "7a8dab", "ffffff", "a45c3e"};

    public static final String FILE_PREFIX = "file:";

    private static final List<ISpaceObject> SPACE_OBJECTS = new ArrayList<>();
    private static final List<IRotatingModel> ROTATING_MODELS = new ArrayList<>();

    @Override
    public void start (Stage primaryStage) {
        final StackPane root = new StackPane();

        final Scene scene = new Scene(root, 1500, 900);

        primaryStage.setHeight(900); primaryStage.setWidth(1500);

        final double initialCentreX = primaryStage.getWidth() / 2;
        final double initialCentreY = primaryStage.getHeight() / 2;

        final Star sun = new Sun(root, initialCentreX - 250);
        ROTATING_MODELS.add(sun); SPACE_OBJECTS.add(sun);

        final Planet earth = new Earth(root, sun);
        ROTATING_MODELS.add(earth); SPACE_OBJECTS.add(earth);

        ROTATING_MODELS.forEach(rotatingModel -> {
            rotatingModel.initModel();
            rotatingModel.setupRotations(initialCentreX, initialCentreY);
        });

        this.createBackgroundStars(root);

        // Add Earth and Sun to root.
        root.getChildren().addAll(earth.getPlanetContainer(), sun.getStarSphere());

        root.getStylesheets().add("file:" + FileUtils.getFile("stylesheets/default.css").getPath());

        primaryStage.setScene(scene);
        primaryStage.show();

        final Timeline tick = new Timeline(60, new KeyFrame(new Duration(10), this::callTickMethods));

        tick.setCycleCount(-1);
        tick.play();
    }

    private void callTickMethods (final ActionEvent event) {
        SPACE_OBJECTS.forEach(spaceObject -> spaceObject.onTick(event));
    }

    private void createBackgroundStars(final StackPane root) {
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
