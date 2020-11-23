package com.harleyoconnor.solarsystem.planets;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.solarsystem.SolarSystem;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

/**
 * @author Harley O'Connor
 */
public final class Earth extends Planet {

    public Earth (final StackPane parentPane) {
        super (25, parentPane);
    }

    @Override
    public void initModel() {
        super.initModel();

        this.planetSphere.setTranslateX(100);
    }

    @Override
    public Image getTexture() {
        return new Image(SolarSystem.FILE_PREFIX + FileUtils.getInternalPath("textures/planets/earth.jpg"));
    }

}
