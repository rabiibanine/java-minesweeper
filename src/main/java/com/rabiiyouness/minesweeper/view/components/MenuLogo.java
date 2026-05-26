package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class MenuLogo {
    private HBox root;

    public MenuLogo() {

        Label logoLabel = new Label("MINESWEEPER");
        logoLabel.getStyleClass().add("menu-logo-label");
        logoLabel.setCache(true);
        logoLabel.setCacheHint(CacheHint.QUALITY);

        FontIcon plusIcon = new FontIcon("ci-add");
        plusIcon.getStyleClass().add("menu-logo-icon");
        plusIcon.setCache(true);
        plusIcon.setCacheHint(CacheHint.SPEED);

        HBox hbox = new HBox(logoLabel, plusIcon);
        hbox.setAlignment(Pos.CENTER);

        StackPane menuLogoWrapper = new StackPane(hbox);
        root = new HBox(menuLogoWrapper);
        root.setAlignment(Pos.CENTER);

    }
    public Parent getComponent() {
        return root;
    }
}
