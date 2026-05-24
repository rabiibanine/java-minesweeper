package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.model.Board;
import com.rabiiyouness.minesweeper.model.Position;
import com.rabiiyouness.minesweeper.model.Tile;
import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.model.enums.TileState;
import com.rabiiyouness.minesweeper.view.components.GameBoard;
import com.rabiiyouness.minesweeper.view.components.TileButton;
import com.rabiiyouness.minesweeper.view.screens.GameView;
import javafx.scene.Parent;
import javafx.scene.input.MouseButton;

import java.util.List;

public class GameController {

    private MainController controller;
    private GameView gameView;
    private Difficulty difficulty;

    private Board board;

    public GameController(MainController controller) {
        board = new Board();
        // TODO implement some sort of way to input the difficulty
        difficulty = Difficulty.BEGINNER;
        board.initializeGame(difficulty);

        this.controller = controller;
        this.gameView = new GameView(board.getRows(), board.getCols());

        bindEvents();
    }

    public GameView getView(){
        return gameView;
    };

    public Parent getRoot() {
        return gameView.getRoot();
    }

    public void bindEvents() {
        // Reset Button
        gameView.getResetButton().setOnAction(event -> {
            board.initializeGame(difficulty);
            gameView.resetBoard();
        });
        // Home Button
        gameView.getHomeButton().setOnAction(event -> controller.handleHomeButton());

        // Board Tiles
        for (int row = 0; row < board.getRows() ; row++) {
            for (int col = 0; col < board.getCols() ; col++) {
                Position pos = new Position(row, col);
                TileButton tileButton = gameView.getTileButton(pos);

                tileButton.getComponent().setOnMouseClicked(event -> {
                        if (event.getButton() == MouseButton.PRIMARY) {
                            handleReveal(pos);
                        } else if (event.getButton() == MouseButton.SECONDARY) {
                            handleFlag(pos);
                        }
                });
            }
        }
    }

    public void handleReveal(Position pos) {
        List<Tile> changed = board.revealTile(pos);
        for (Tile tile : changed) {
            updateTileView(tile);
        }
    }

    public void handleFlag(Position pos) {
        Tile tile = board.getTileAt(pos);
        board.toggleFlag(pos);
        updateTileView(tile);
    }

    public void updateTileView(Tile tile) {
        TileButton tileButton = gameView.getTileButton(tile.getPosition());

        switch (tile.getState()) {
            case HIDDEN -> tileButton.setHidden();
            case REVEALED -> {
                if (!tile.isMine()) tileButton.setRevealed(tile.getAdjacentMines());
                else tileButton.setMine();
            }
            case FLAGGED -> tileButton.setFlagged();
        }
    }
}
