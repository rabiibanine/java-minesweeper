package com.rabiiyouness.minesweeper.view.components;

import com.rabiiyouness.minesweeper.model.Tile;
import com.rabiiyouness.minesweeper.model.enums.TileState;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

public class TileButton {

    private StackPane root;
    private TileState tileState;
    private int row;
    private int col;

    public TileButton(int row, int col) {
        this.tileState = TileState.HIDDEN;
        this.row = row;
        this.col = row;

        root = new StackPane();
        root.getStyleClass().add("tile");
        root.getStyleClass().add("tile-hidden");

        root.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                handleReveal();
            } else if (e.getButton() == MouseButton.SECONDARY) {
                handleFlag();
            }
        });

    }

    public Parent getComponent() {
        return root;
    }

    public void handleReveal(){
        if (!(tileState == TileState.HIDDEN)) return;
        root.getStyleClass().remove("tile-hidden");
        root.getStyleClass().add("tile-revealed");
    };

    public void handleFlag() {
        System.out.println("FLAGGED ROW(" + row + "), COL(" + col+")");
    }
}
