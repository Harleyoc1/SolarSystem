package com.harleyoconnor.solarsystem;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
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

    private final Circle asteroid;
    private final Scene scene;
    private final List<Direction> currentlyMovingIn = new ArrayList<>();

    public Asteroid (final Scene scene) {
        this.scene = scene;
        this.asteroid = new Circle(5, 5, 10, Paint.valueOf("#ddc618"));

        this.setupMovement();
    }

    private void setupMovement () {
        this.scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W: if (!this.currentlyMovingIn.contains(Direction.NORTH)) this.currentlyMovingIn.add(Direction.NORTH); break;
                case A: if (!this.currentlyMovingIn.contains(Direction.EAST)) this.currentlyMovingIn.add(Direction.EAST); break;
                case S: if (!this.currentlyMovingIn.contains(Direction.SOUTH)) this.currentlyMovingIn.add(Direction.SOUTH); break;
                case D: if (!this.currentlyMovingIn.contains(Direction.WEST)) this.currentlyMovingIn.add(Direction.WEST); break;
            }
        });

        this.scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W -> this.currentlyMovingIn.remove(Direction.NORTH);
                case A -> this.currentlyMovingIn.remove(Direction.EAST);
                case S -> this.currentlyMovingIn.remove(Direction.SOUTH);
                case D -> this.currentlyMovingIn.remove(Direction.WEST);
            }
        });
    }

    @Override
    public void onTick (final ActionEvent event) {
        final double boundY = this.scene.getHeight() / 2 - 8;
        final double boundX = this.scene.getWidth() / 2 - 8;

        double newX = this.asteroid.getTranslateX();
        double newY = this.asteroid.getTranslateY();

        for (final Direction dir : this.currentlyMovingIn) {
            newX += (!dir.isVertical() ? 5 * dir.getMovement() : 0);
            newY += (dir.isVertical() ? 5 * dir.getMovement() : 0);
        }

        if (boundX < newX) newX = boundX;
        if (-boundX > newX) newX = -boundX;
        if (boundY < newY) newY = boundY;
        if (-boundY > newY) newY = -boundY;

        this.asteroid.setTranslateX(newX);
        this.asteroid.setTranslateY(newY);
    }

    public Circle getAsteroid() {
        return asteroid;
    }

}
