package com.harleyoconnor.solarsystem;

import javafx.event.ActionEvent;

/**
 * Common abstract methods for space objects.
 *
 * @author Harley O'Connor
 */
public interface ISpaceObject {

    /**
     * Called for every tick. In this case a tick is every 10ms.
     *
     * @param event The action event.
     */
    void onTick (final ActionEvent event);

}
