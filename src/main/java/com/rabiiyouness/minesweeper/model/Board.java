package com.rabiiyouness.minesweeper.model;

import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.model.enums.GameState;
import com.rabiiyouness.minesweeper.model.enums.TileState;

import java.util.*;

public class Board{
    private final int rows;
    private final int columns;
    private final int mines;
    private final Difficulty difficulty;
    private final Tile[][] tiles;
    private int flagsPlaced;
    private int revealedCount;
    private int elapsedSeconds;
    private GameState gameState = GameState.READY;

    public Board(int rows, int columns, int mines, Difficulty difficulty) {
        if (rows < 5 || columns < 5 || mines < 1 || mines >= rows * columns) {
            throw new IllegalArgumentException("Invalid board dimensions or mine count.");
        }
        this.rows = rows;
        this.columns = columns;
        this.mines = mines;
        this.difficulty = difficulty;
        this.tiles = new Tile[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                tiles[r][c] = new Tile(r, c);
            }
        }
    }

    /**
     * return true si la position est valide, sinon false
     */
    public boolean isInside(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }


    /**
     * Retourne la case située à la position donnée.
     * throws IllegalArgumentException si la position est hors de la grille
     */
    public Tile tileAt(int row, int column) {
        if (!isInside(row, column)) {
            throw new IllegalArgumentException("Position outside board.");
        }
        return tiles[row][column];
    }


    /**
     * Retourne toutes les cases voisines autour d'une case donnée.
     */
    public List<Tile> neighborsOf(int row, int column) {
        List<Tile> neighbors = new ArrayList<>(8);
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) {
                    continue;
                }
                int nr = row + dr;
                int nc = column + dc;
                if (isInside(nr, nc)) {
                    neighbors.add(tileAt(nr, nc));
                }
            }
        }
        return neighbors;
    }


    /**
     * Recalcule le nombre de drapeaux placés
     * ainsi que le nombre de cases révélées.
     */
    public void resetCounters() {
        flagsPlaced = 0;
        revealedCount = 0;
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile.getState() == TileState.FLAGGED) {
                    flagsPlaced++;
                } else if (tile.getState() == TileState.REVEALED) {
                    revealedCount++;
                }
            }
        }
    }

    public int getRows() { return rows; }
    public int getColumns() { return columns; }
    public int getMines() { return mines; }
    public Difficulty getDifficulty() { return difficulty; }
    public Tile[][] getTiles() { return tiles; }
    public int getFlagsPlaced() { return flagsPlaced; }
    public void incrementFlags() { flagsPlaced++; }
    public void decrementFlags() { flagsPlaced = Math.max(0, flagsPlaced - 1); }
    public int getRevealedCount() { return revealedCount; }
    public void incrementRevealedCount() { revealedCount++; }
    public int getRemainingMinesEstimate() { return mines - flagsPlaced; }
    public int getElapsedSeconds() { return elapsedSeconds; }
    public void setElapsedSeconds(int elapsedSeconds) { this.elapsedSeconds = elapsedSeconds; }
    public GameState getGameState() { return gameState; }
    public void setGameState(GameState gameState) { this.gameState = gameState; }
}
