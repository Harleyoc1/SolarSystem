package com.harleyoconnor.solarsystem;

import com.harleyoconnor.solarsystem.planets.Planet;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harley O'Connor
 */
public final class Asteroid implements ITickable {

    public enum Direction {
        NORTH(true, -1), SOUTH(true, 1), EAST(false, -1), WEST(false, 1);

        private final boolean vertical;
        private final int movement;

        Direction (final boolean vertical, final int movement) {
            this.vertical = vertical;
            this.movement = movement;
        }

        public boolean isVertical() {
            return vertical;
        }

        public int getMovement() {
            return movement;
        }

    }

    private int score = 0;

    private final Planet targetPlanet;
    private final Circle asteroid;
    private final Scene scene;
    private final List<Direction> currentlyMovingIn = new ArrayList<>();

    public Asteroid (final Scene scene, final Planet targetPlanet) {
        this.scene = scene;
        this.targetPlanet = targetPlanet;
        this.asteroid = new Circle(5, 5, 10, Paint.valueOf("#ddc618"));

        this.setupMovement();
    }

    private void setupMovement () {
        this.scene.setOnKeyPressed(event -> this.keyPressedOrReleased(event, true));
        this.scene.setOnKeyReleased(event -> this.keyPressedOrReleased(event, false));
    }

    private void keyPressedOrReleased (final KeyEvent event, final boolean pressed) {
        switch (event.getCode()) {
            case W -> removeOrAddDir(Direction.NORTH, pressed);
            case A -> removeOrAddDir(Direction.EAST, pressed);
            case S -> removeOrAddDir(Direction.SOUTH, pressed);
            case D -> removeOrAddDir(Direction.WEST, pressed);
        }
    }

    private void removeOrAddDir (final Direction dir, final boolean pressed) {
        if (pressed && !this.currentlyMovingIn.contains(dir)) this.currentlyMovingIn.add(dir);
        else if (!pressed) this.currentlyMovingIn.remove(dir);
    }

    @Override
    public void onTick (final ActionEvent event) {
        final double boundY = this.scene.getHeight() / 2 - 8;
        final double boundX = this.scene.getWidth() / 2 - 8;

        double newX = this.asteroid.getTranslateX();
        double newY = this.asteroid.getTranslateY();

        for (final Direction dir : this.currentlyMovingIn) {
            newX += (!dir.isVertical() ? 3 * dir.getMovement() : 0);
            newY += (dir.isVertical() ? 3 * dir.getMovement() : 0);
        }

        if (boundX < newX) newX = boundX;
        if (-boundX > newX) newX = -boundX;
        if (boundY < newY) newY = boundY;
        if (-boundY > newY) newY = -boundY;

        this.asteroid.setTranslateX(newX);
        this.asteroid.setTranslateY(newY);

        if (this.asteroid.getBoundsInParent().intersects(this.targetPlanet.getPlanetContainer().getBoundsInParent())) this.incrementScore();
    }

    private void incrementScore() {
        SolarSystem.INSTANCE.addToScoreLabel(1);
        this.score++;
    }

    public Circle getAsteroid() {
        return asteroid;
    }

}
