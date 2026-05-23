package com.rabiiyouness.minesweeper.view.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MenuLogo {
    public Pane getComponent() {
        StackPane menuLogo = new StackPane();

        menuLogo.getStyleClass().add("menu-logo");

        Label logoLabel = new Label("MINESWEEPER +");

        menuLogo.getChildren().add(logoLabel);

        return menuLogo;
    }
}
