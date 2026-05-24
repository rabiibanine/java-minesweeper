package com.rabiiyouness.minesweeper.view.components;

import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class TileButton {

    private StackPane root;
    private FontIcon flagIcon;
    private FontIcon mineIcon;

    public TileButton() {

        root = new StackPane();
        root.getStyleClass().setAll("tile", "tile-hidden");

        flagIcon = new FontIcon("ci-flag-filled");
        flagIcon.getStyleClass().setAll("board-icon", "board-flag-icon");
        root.getChildren().add(flagIcon);
        flagIcon.setVisible(false);

        mineIcon = new FontIcon("mdi2m-mine");
        flagIcon.getStyleClass().setAll("board-icon", "board-mine-icon");
        root.getChildren().add(mineIcon);
        mineIcon.setVisible(false);

    }

    public Parent getComponent() {
        return root;
    }

    public void setRevealed() {
        root.getStyleClass().setAll("tile", "tile-revealed");
    }

    public void setFlagged() {
        flagIcon.setVisible(true);
    }

    public void setHidden() {
        root.getStyleClass().setAll("tile", "tile-hidden");
        flagIcon.setVisible(false);
    }

    public void reset() {
        root.getStyleClass().setAll("tile", "tile-hidden");
        flagIcon.setVisible(false);
        mineIcon.setVisible(false);
    }
}
