package com.rabiiyouness.minesweeper.view.components;


import com.rabiiyouness.minesweeper.model.enums.TileState;
import com.rabiiyouness.minesweeper.model.Tile;
import javafx.scene.control.Button;

public class TileButton extends Button {
    private final int row;
    private final int column;

    public TileButton(int row, int column) {
        this.row = row;
        this.column = column;
        getStyleClass().add("tile");
        setMinSize(28, 28);
        setPrefSize(34, 34);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setFocusTraversable(false);
    }

    public void render(Tile tile) {
        getStyleClass().removeIf(style -> style.startsWith("number-") || style.equals("tile-revealed") || style.equals("tile-flagged") || style.equals("tile-mine"));
        setText("");
        if (tile.getState() == TileState.FLAGGED) {
            getStyleClass().add("tile-flagged");
            setText("⚑");
        } else if (tile.getState() == TileState.REVEALED) {
            getStyleClass().add(tile.isMine() ? "tile-mine" : "tile-revealed");
            if (tile.isMine()) {
                setText("✹");
            } else if (tile.getAdjacentMines() > 0) {
                setText(Integer.toString(tile.getAdjacentMines()));
                getStyleClass().add("number-" + tile.getAdjacentMines());
            }
        }
    }

    public int getRow() { return row; }
    public int getColumn() { return column; }
}

