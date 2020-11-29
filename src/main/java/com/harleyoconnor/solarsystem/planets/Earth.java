package com.harleyoconnor.solarsystem.planets;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.solarsystem.SolarSystem;
import com.harleyoconnor.solarsystem.stars.Star;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

/**
 * @author Harley O'Connor
 */
public final class Earth extends Planet {

    public Earth (final StackPane parentPane, final Star parentStar) {
        super (25, parentPane, parentStar, 100, 0);
    }

    @Override
    public void initModel() {
        super.initModel();

        this.planetSphere.setTranslateX(100);
    }

    @Override
    public void initRotations(double centreX, double centreY) {
        super.initRotations(centreX - 250, centreY);
    }

    @Override
    public Image getTexture() {
        return new Image(SolarSystem.FILE_PREFIX + FileUtils.getInternalPath("textures/planets/earth.jpg"));
    }

}
