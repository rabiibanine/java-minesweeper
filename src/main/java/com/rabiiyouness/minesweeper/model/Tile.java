package com.rabiiyouness.minesweeper.model;

import com.rabiiyouness.minesweeper.model.enums.TileState;

public class Tile {
    private final Position position;
    private boolean mine;
    private int adjacentMines;
    private TileState state = TileState.HIDDEN;

    public Tile(int row, int column) {
        this.position = new Position(row, column);
    }

    public Position getPosition() { return position; }
    public boolean isMine() { return mine; }
    public void setMine(boolean mine) { this.mine = mine; }
    public int getAdjacentMines() { return adjacentMines; }
    public void setAdjacentMines(int adjacentMines) { this.adjacentMines = adjacentMines; }
    public TileState getState() { return state; }
    public void setState(TileState state) { this.state = state; }
    public boolean isHidden() { return state == TileState.HIDDEN; }
    public boolean isRevealed() { return state == TileState.REVEALED; }
    public boolean isFlagged() { return state == TileState.FLAGGED; }
}
