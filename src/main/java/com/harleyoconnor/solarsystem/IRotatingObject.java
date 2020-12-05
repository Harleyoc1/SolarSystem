package com.harleyoconnor.solarsystem;

import javafx.event.ActionEvent;

/**
 * Common abstract methods for Rotating models.
 *
 * @author Harley O'Connor
 */
public interface IRotatingObject {

    /**
     * Implement this method to initialise models.
     */
    void initModel();

    /**
     * Implement this method to setup the model rotations.
     *
     * @param centreX The initial x centre of the screen.
     * @param centreY The initial y centre of the screen.
     */
    void initRotations(final double centreX, final double centreY);

    /**
     * Called for every tick. In this case a tick is every 10ms.
     *
     * @param event The action event.
     */
    void onTick (final ActionEvent event);

}
