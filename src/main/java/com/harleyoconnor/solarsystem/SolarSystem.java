package com.harleyoconnor.solarsystem;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.javautilities.IntegerUtils;
import com.harleyoconnor.solarsystem.moons.Luna;
import com.harleyoconnor.solarsystem.moons.Moon;
import com.harleyoconnor.solarsystem.planets.Earth;
import com.harleyoconnor.solarsystem.planets.Planet;
import com.harleyoconnor.solarsystem.stars.Star;
import com.harleyoconnor.solarsystem.stars.Sun;
import com.harleyoconnor.solarsystem.util.InterfaceUtils;
import com.harleyoconnor.solarsystem.util.StyleUtils;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class SolarSystem extends Application {

    final String[] backgroundStarColours = {"a37374", "7a8dab", "ffffff", "a45c3e"};

    public static SolarSystem INSTANCE; // Effectively final - set in start method below.
    public static final String FILE_PREFIX = "file:";

    private Asteroid asteroid;

    private final Label scoreLabel = StyleUtils.addStyleClassTo(InterfaceUtils.createLabel("0"), "score-font");
    private final List<IRotatingObject> rotatingObjects = new ArrayList<>();
    private final List<ITickable> tickableObjects = new ArrayList<>();

    @Override
    public void start (Stage primaryStage) throws MalformedURLException {
        INSTANCE = this;

        final StackPane root = new StackPane();

        final Scene scene = new Scene(root, 1500, 900);

        primaryStage.setHeight(900); primaryStage.setWidth(1500);

        final double initialCentreX = primaryStage.getWidth() / 2;
        final double initialCentreY = primaryStage.getHeight() / 2;

        final Star sun = new Sun(root, initialCentreX - 250);
        this.addRotatingSpaceObject(sun);

        final Planet earth = new Earth(root, sun);
        this.addRotatingSpaceObject(earth);

        final Moon luna = new Luna(root, earth);
        this.addRotatingSpaceObject(luna);

        this.initRotatingModels(initialCentreX, initialCentreY);

        this.createBackgroundStars(root);

        // Add Earth and Sun to root.
        root.getChildren().addAll(earth.getPlanetContainer(), sun.getStarSphere(), luna.getMoonContainer());

        root.getStylesheets().add(FileUtils.getFile("stylesheets/default.css").toURI().toURL().toExternalForm());

        this.asteroid = new Asteroid(scene, earth);
        root.getChildren().addAll(this.asteroid.getAsteroid(),
                InterfaceUtils.addElementsToPane(new VBox(),
                        InterfaceUtils.addElementsToPane(new HBox(),
                                InterfaceUtils.createSpacer(true),
                                StyleUtils.addStyleClassTo(InterfaceUtils.createLabel("Score: "), "score-font"),
                                this.scoreLabel),
                        InterfaceUtils.createSpacer(false)
                ));

        this.tickableObjects.addAll(this.rotatingObjects);
        this.tickableObjects.add(this.asteroid);

        final Timeline tick = new Timeline(60, new KeyFrame(new Duration(10), this::callTickMethods));

        tick.setCycleCount(-1);
        tick.play();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addRotatingSpaceObject (final IRotatingObject rotatingObject) {
        this.rotatingObjects.add(rotatingObject);
    }

    private void initRotatingModels(double initialCentreX, double initialCentreY) {
        this.rotatingObjects.forEach(rotatingModel -> {
            rotatingModel.initModel();
            rotatingModel.initRotations(initialCentreX, initialCentreY);
        });
    }

    private void callTickMethods (final ActionEvent event) {
        this.tickableObjects.forEach(spaceObject -> spaceObject.onTick(event));
    }

    private void createBackgroundStars(final StackPane root) {
        for (int i = 0; i < IntegerUtils.getRandomIntBetween(5000, 5500); i++) {
            final Circle star = new Circle(IntegerUtils.getRandomIntBetween(1, 50) == 1 ? 3 : IntegerUtils.getRandomIntBetween(1, 9) == 1 ? 2 : 1);
            star.setFill(Paint.valueOf("#" + this.backgroundStarColours[IntegerUtils.getRandomIntBetween(0, this.backgroundStarColours.length - 1)]));

            star.setTranslateX(IntegerUtils.getRandomIntBetween(-1200, 1200));
            star.setTranslateY(IntegerUtils.getRandomIntBetween(-1200, 1200));

            root.getChildren().add(star);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void addToScoreLabel (final int amountToAdd) {
        this.scoreLabel.setText(Integer.toString(Integer.parseInt(this.scoreLabel.getText()) + amountToAdd));
    }

    public void takeFromScoreLabel (final int amountToTake) {
        this.scoreLabel.setText(Integer.toString(Integer.parseInt(this.scoreLabel.getText()) - amountToTake));
    }

}
