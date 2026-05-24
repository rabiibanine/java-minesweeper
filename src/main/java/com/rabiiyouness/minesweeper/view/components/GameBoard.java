package com.rabiiyouness.minesweeper.view.components;

import com.rabiiyouness.minesweeper.model.Position;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class GameBoard {

    private StackPane root;
    private GridPane grid;
    private TileButton[][] tileButtons;
    private int rows;
    private int cols;

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new GridPane();
        grid.getStyleClass().add("board-grid");

        tileButtons = new TileButton[rows][cols];

        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols ; j++) {
                tileButtons[i][j] = new TileButton();
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

    public TileButton getTileButton(Position pos) {
        return tileButtons[pos.row()][pos.column()];
    }

    public Parent getComponent() {
        return root;
    }

    public void resetGameBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                tileButtons[row][col].reset();
            }
        }
    }
}
