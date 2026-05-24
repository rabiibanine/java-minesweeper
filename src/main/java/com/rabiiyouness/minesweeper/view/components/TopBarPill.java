package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class TopBarPill {
    private final HBox root;
    private final Label label;

    public TopBarPill(String iconCode) {
        FontIcon icon = new FontIcon(iconCode);
        icon.getStyleClass().add("topbar-pill-icon");

        label = new Label("000");
        label.getStyleClass().add("topbar-pill-label");

        root = new HBox(icon, label);
        root.getStyleClass().add("topbar-pill");
        root.setAlignment(Pos.CENTER);
    }

    public void setValue(String value) {
        label.setText(value);
    }

    public Parent getComponent() { return root; }
}
