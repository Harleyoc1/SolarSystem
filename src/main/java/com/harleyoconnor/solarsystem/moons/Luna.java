package com.harleyoconnor.solarsystem.moons;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.solarsystem.SolarSystem;
import com.harleyoconnor.solarsystem.planets.Planet;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

/**
 * @author Harley O'Connor
 */
public final class Luna extends Moon {

    public Luna (StackPane parentPane, Planet parentPlanet) {
        super(10, parentPane, parentPlanet);
    }
    
    @Override
    public Image getTexture() {
        return new Image(SolarSystem.FILE_PREFIX + FileUtils.getInternalPath("textures/planets/earth.jpg"));
    }

}
