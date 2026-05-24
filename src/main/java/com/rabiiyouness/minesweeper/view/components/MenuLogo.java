package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MenuLogo {
    private HBox root;

    public MenuLogo() {

        Label logoLabel = new Label("MINESWEEPER +");
        logoLabel.getStyleClass().add("menu-logo");
        logoLabel.setCache(true);
        logoLabel.setCacheHint(CacheHint.QUALITY);

        StackPane menuLogoWrapper = new StackPane(logoLabel);
        root = new HBox(menuLogoWrapper);
        root.setAlignment(Pos.CENTER);

    }
    public Parent getComponent() {
        return root;
    }
}
