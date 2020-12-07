package com.harleyoconnor.solarsystem.util;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * @author Harley O'Connor
 */
public final class InterfaceUtils {

    public static ImageView createImageView (final String imagePath, final int width, final int height) {
        final ImageView imageView = new ImageView(new Image(imagePath));

        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        return imageView;
    }

    public static Region createSpacer (final boolean horizontal) {
        final Region spacer = new Region();
        if (horizontal) HBox.setHgrow(spacer, Priority.ALWAYS);
        else VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public static <T extends Pane> T centraliseElementsInBox (final T pane, final Node... nodes) {
        if (!(pane instanceof HBox || pane instanceof VBox)) return pane;

        addSpacerTo(pane, pane instanceof HBox);
        addElementsToPane(pane, nodes);
        addSpacerTo(pane, pane instanceof HBox);

        return pane;
    }

    public static <T extends Pane> T addSpacerTo (final T pane, final boolean horizontal) {
        pane.getChildren().add(createSpacer(horizontal));
        return pane;
    }

    public static <T extends Pane> T addElementsToPane (final T pane, final Node... nodes) {
        pane.getChildren().addAll(nodes);
        return pane;
    }

    public static <T extends Pane> T removeElementsFromPane (final T pane, final Node... nodes) {
        pane.getChildren().removeAll(nodes);
        return pane;
    }

    public static Label createLabel (final String initialText, final Font font) {
        final Label label = createLabel(initialText);
        label.setFont(font);
        return label;
    }

    public static Label createLabel (final String initialText) {
        final Label label = new Label(initialText);
        label.setWrapText(true);
        return label;
    }

    public static <T extends Region> T setSizeOfNode (final T node, final int width, final int height) {
        node.setMinWidth(width);
        node.setMinHeight(height);

        return node;
    }

    public static <T extends Region> T addTopPadding (final T region, final int padding) {
        region.setPadding(new Insets(padding, 0, 0, 0));
        return region;
    }

    public static <T extends Node> T addStyleClassesTo (final T node, final String... styleClasses) {
        node.getStyleClass().addAll(styleClasses);
        return node;
    }

}