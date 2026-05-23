package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MenuLogo {
    public Parent getComponent() {

        Label logoLabel = new Label("MINESWEEPER +");
        logoLabel.getStyleClass().add("menu-logo");

        StackPane menuLogoWrapper = new StackPane(logoLabel);
        HBox menuLogo = new HBox(menuLogoWrapper);
        menuLogo.setAlignment(Pos.CENTER);

        return menuLogo;
    }
}
