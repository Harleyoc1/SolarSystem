package com.harleyoconnor.solarsystem;

/**
 * Common abstract methods for Rotating models.
 *
 * @author Harley O'Connor
 */
public interface IRotatingObject extends ITickable {

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

}
