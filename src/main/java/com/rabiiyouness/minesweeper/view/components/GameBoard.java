package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class GameBoard {

    private StackPane root;
    private GridPane grid;
    private TileButton[][] tileButtons;

    public GameBoard() {
        grid = new GridPane();
        grid.getStyleClass().add("board-grid");

        tileButtons = new TileButton[10][10];

        for (int i = 0; i < 10 ; i++) {
            for (int j = 0; j < 10 ; j++) {
                tileButtons[i][j] = new TileButton(i, j);
                grid.add(tileButtons[i][j].getComponent(), j, i);
            }
        }

        root = new StackPane();
        root.getStyleClass().add("board-container");
        root.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        root.getChildren().add(grid);
    }

    public TileButton[][] getTileButtons() {
        return tileButtons;
    }

    public TileButton getTileButton(int row, int col) {
        return tileButtons[row][col];
    }

    public Parent getComponent() {
        return root;
    }
}
