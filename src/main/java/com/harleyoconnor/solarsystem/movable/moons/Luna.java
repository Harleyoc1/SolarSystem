package com.harleyoconnor.solarsystem.movable.moons;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.solarsystem.Constants;
import com.harleyoconnor.solarsystem.movable.planets.Planet;
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
    public void initModel() {
        super.initModel();

        this.moonSphere.setTranslateX(35);
    }

    @Override
    public Image getTexture() {
        return new Image(Constants.FILE_PREFIX + FileUtils.getInternalPath("textures/planets/earth.jpg"));
    }

}
