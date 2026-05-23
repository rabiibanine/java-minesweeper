package com.rabiiyouness.minesweeper.model;

import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.model.enums.GameState;
import com.rabiiyouness.minesweeper.model.enums.TileState;

import java.time.LocalDateTime;
import java.util.*;

public class Board {

    private Tile[][]   tiles;
    private int        rows;
    private int        columns;
    private int        mines;
    private Difficulty difficulty;

    private GameState gameState      = GameState.READY;
    private int       flagsPlaced    = 0;
    private int       revealedTiles  = 0;
    private int       elapsedSeconds = 0;
    private Score     score;

    /**
     * Réinitialise tout l’état du jeu et construit une grille vide.
     * Le placement des mines est reporté jusqu’au premier appel de revealTile()
     * afin que la première case cliquée soit toujours sûre.
     */
    public void initializeGame(Difficulty difficulty) {
        this.difficulty = difficulty;
        this.rows       = difficulty.getRows();
        this.columns    = difficulty.getColumns();
        this.mines      = difficulty.getMines();

        // creer un grille vide
        tiles = new Tile[rows][columns];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                tiles[r][c] = new Tile(r, c);
            }
        }
        gameState      = GameState.READY;
        flagsPlaced    = 0;
        revealedTiles  = 0;
        elapsedSeconds = 0;
        score          = null;
    }

    /**
     * Appelée lors d’un clic gauche.
     * Révèle la case située à la position {@code pos}.
     */
    public List<Tile> revealTile(Position pos) {
        if (canInteract()) { return List.of(); }

        // premiere click → finalise la grille maintenant et placer les mines
        if (gameState == GameState.READY) {
            placeMines(pos);
            calculateAdjacentMines();
            gameState = GameState.RUNNING;
        }

        Tile tile = getTileAt(pos);

        if (tile.getState() == TileState.FLAGGED
                || tile.getState() == TileState.REVEALED) {
            return List.of();
        }

        List<Tile> changed = new ArrayList<>();

        if (tile.isMine()) {
            // Mine touchée → défaite
            tile.setState(TileState.REVEALED);
            changed.add(tile);
            revealedTiles++;
            gameState = GameState.LOST;
            revealAllMines(changed);
            return changed;
        }

        //Case sûre → révélation en cascade
        floodReveal(tile, changed);

        if (checkWin()) {
            gameState = GameState.WON;
            autoFlagRemainingMines();
            score = new Score(difficulty, elapsedSeconds, LocalDateTime.now());
        }

        return changed;
    }

    /**
     * Appelée lors d’un clic droit.
     * Change l’état de la case {@code pos}
     * entre HIDDEN et FLAGGED.
     */
    public void toggleFlag(Position pos) {
        if (canInteract()) { return; }

        Tile tile = getTileAt(pos);
        if (tile.getState() == TileState.REVEALED) { return; }

        if (tile.getState() == TileState.FLAGGED) {
            tile.setState(TileState.HIDDEN);
            flagsPlaced = Math.max(0, flagsPlaced - 1);
        } else {
            tile.setState(TileState.FLAGGED);
            flagsPlaced++;
        }
    }

    // =========================================================================
    // GETTERS
    // =========================================================================
    public GameState getGameState() { return gameState; }
    public int getRemainingMines() { return mines - flagsPlaced; }
    public Score getScore() { return score; }
    /**
     * @return la case située à cette position
     * @throws IllegalArgumentException si la position est hors de la grille
     */
    public Tile getTileAt(Position pos) {
        if (!isInside(pos.row(), pos.column())) {
            throw new IllegalArgumentException("Position hors limites : " + pos);
        }
        return tiles[pos.row()][pos.column()];
    }
    public int getRows() { return rows; }
    public int getCols() { return columns; }

    // =========================================================================
    // Fonctions utilitaires
    // =========================================================================

    private void placeMines(Position firstClick) {
        List<Position> candidates = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                if (!isProtected(firstClick, r, c)) {
                    candidates.add(new Position(r, c));
                }
            }
        }
        if (mines > candidates.size()) {
            throw new IllegalStateException("Trop de mines pour la taille de la grille.");
        }
        Collections.shuffle(candidates);
        for (int i = 0; i < mines; i++) {
            getTileAt(candidates.get(i)).setMine(true);
        }
    }

    private void calculateAdjacentMines() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                Tile tile = tiles[r][c];
                if (tile.isMine()) { continue; }
                long count = neighborsOf(tile.getPosition())
                        .stream().filter(Tile::isMine).count();
                tile.setAdjacentMines((int) count);
            }
        }
    }

    private void floodReveal(Tile tile, List<Tile> changed) {
        if (tile.getState() == TileState.REVEALED
                || tile.getState() == TileState.FLAGGED)
        { return; }

        tile.setState(TileState.REVEALED);
        changed.add(tile);
        revealedTiles++;

        if (tile.getAdjacentMines() > 0) { return; }   // numbered tile — stop

        for (Tile neighbor : neighborsOf(tile.getPosition())) {
            floodReveal(neighbor, changed);
        }
    }

    private void revealAllMines(List<Tile> changed) {
        for (Tile[] row : tiles) {
            for (Tile t : row) {
                if (t.isMine() && t.getState() != TileState.REVEALED) {
                    t.setState(TileState.REVEALED);
                    changed.add(t);
                }
            }
        }
    }

    private void autoFlagRemainingMines() {
        for (Tile[] row : tiles) {
            for (Tile t : row) {
                if (t.isMine()) { t.setState(TileState.FLAGGED); }
            }
        }
        flagsPlaced = mines;
    }

    private boolean checkWin() {
        return revealedTiles >= (rows * columns - mines);
    }

    private boolean canInteract() {
        return gameState != GameState.READY && gameState != GameState.RUNNING;
    }

    private boolean isInside(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < columns;
    }

    private boolean isProtected(Position firstClick, int row, int col) {
        return Math.abs(firstClick.row()    - row) <= 1
                && Math.abs(firstClick.column() - col) <= 1;
    }

    private List<Tile> neighborsOf(Position pos) {
        List<Tile> neighbors = new ArrayList<>(8);
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                if (dr == 0 && dc == 0) { continue; }
                int nr = pos.row() + dr, nc = pos.column() + dc;
                if (isInside(nr, nc)) {
                    neighbors.add(tiles[nr][nc]);
                }
            }
        }
        return neighbors;
    }


    public void tickSecond() {
        if (gameState == GameState.RUNNING) { elapsedSeconds++; }
    }
    public int getElapsedSeconds() { return elapsedSeconds; }
}