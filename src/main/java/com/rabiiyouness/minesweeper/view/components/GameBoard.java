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
        grid = new GridPane();
        grid.getStyleClass().add("board-grid");

        root = new StackPane();
        root.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        root.getChildren().add(grid);

        rebuildBoard(rows, cols);
    }

    public void rebuildBoard(int newRows, int newCols) {
        int rows = newRows;
        int cols = newCols;

        grid.getChildren().clear();

        tileButtons = new TileButton[rows][cols];

        for (int i = 0; i < rows ; i++) {
            for (int j = 0; j < cols ; j++) {
                tileButtons[i][j] = new TileButton();
                grid.add(tileButtons[i][j].getComponent(), j, i);
            }
        }
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

}
