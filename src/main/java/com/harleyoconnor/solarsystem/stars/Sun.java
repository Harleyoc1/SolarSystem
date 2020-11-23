package com.harleyoconnor.solarsystem.stars;

import com.harleyoconnor.javautilities.FileUtils;
import com.harleyoconnor.solarsystem.SolarSystem;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

/**
 * @author Harley O'Connor
 */
public final class Sun extends Star {

    public Sun (final StackPane parentPane, final double centreX) {
        super(150, parentPane, centreX - 250, 0);
    }

    @Override
    public void initModel() {
        super.initModel();
        this.starSphere.setTranslateX(-250);
    }

    @Override
    public Image getTexture() {
        return new Image(SolarSystem.FILE_PREFIX + FileUtils.getInternalPath("textures/stars/sun.jpg"));
    }

}
