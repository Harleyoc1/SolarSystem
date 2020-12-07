package com.harleyoconnor.solarsystem.util;

import javafx.scene.Node;

/**
 * @author Harley O'Connor
 */
public final class StyleUtils {

    public static <T extends Node> T addStyleClassTo (final T node, final String styleClass) {
        node.getStyleClass().add(styleClass);
        return node;
    }

}
