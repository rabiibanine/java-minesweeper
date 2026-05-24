package com.rabiiyouness.minesweeper.view.components;

import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class TileButton {

    private StackPane root;
    private FontIcon flagIcon;
    private FontIcon mineIcon;
    private Label numberLabel;

    public TileButton() {

        root = new StackPane();
        root.getStyleClass().setAll("tile", "tile-hidden");

        flagIcon = new FontIcon("ci-flag-filled");
        flagIcon.getStyleClass().setAll("board-icon", "board-flag-icon");
        flagIcon.setVisible(false);
        root.getChildren().add(flagIcon);

        mineIcon = new FontIcon("mdi2m-mine");
        mineIcon.getStyleClass().setAll("board-icon", "board-mine-icon");
        mineIcon.setVisible(false);
        root.getChildren().add(mineIcon);

        numberLabel = new Label();
        numberLabel.getStyleClass().setAll("tile-number");
        numberLabel.setVisible(false);
        numberLabel.setCache(true);
        numberLabel.setCacheHint(CacheHint.QUALITY);
        root.getChildren().add(numberLabel);

    }

    public Parent getComponent() {
        return root;
    }

    public void setRevealed(int adjacentMinesCount) {
        root.getStyleClass().setAll("tile", "tile-revealed");
        if (adjacentMinesCount > 0) {
            numberLabel.setText(String.valueOf(adjacentMinesCount));
            numberLabel.getStyleClass().add("tile-" + adjacentMinesCount);
            numberLabel.setVisible(true);
        } else {
            numberLabel.setVisible(false);
        }
    }

    public void setFlagged() {
        flagIcon.setVisible(true);
    }

    public void setMine() {
        root.getStyleClass().setAll("tile", "tile-revealed", "tile-mine");
        mineIcon.setVisible(true);
    }

    public void setHidden() {
        root.getStyleClass().setAll("tile", "tile-hidden");
        flagIcon.setVisible(false);
        mineIcon.setVisible(false);
        numberLabel.setVisible(false);
    }

}
