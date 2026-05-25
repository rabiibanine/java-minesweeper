package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.model.Board;
import com.rabiiyouness.minesweeper.model.Position;
import com.rabiiyouness.minesweeper.model.Tile;
import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.model.enums.GameState;
import com.rabiiyouness.minesweeper.util.PopupFactory;
import com.rabiiyouness.minesweeper.util.TimeFormatter;
import com.rabiiyouness.minesweeper.view.components.PopupConfig;
import com.rabiiyouness.minesweeper.view.components.TileButton;
import com.rabiiyouness.minesweeper.view.screens.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import java.sql.Time;
import java.util.List;

public class GameController {

    private MainController controller;
    private GameView gameView;
    private Difficulty difficulty;
    private Timeline timer;

    private Board board;

    public GameController(MainController controller) {
        board = new Board();
        // TODO implement some sort of way to input the difficulty
        difficulty = Difficulty.BEGINNER;
        board.initializeGame(difficulty);

        this.controller = controller;
        gameView = new GameView(board.getRows(), board.getCols());

        // Initialize pills
        gameView.updateFlagPill(board.getRemainingMines());

        // Start timer
        startTimer();

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
        gameView.getResetButton().setOnAction(event -> handleReset());

        // Home Button
        gameView.getHomeButton().setOnAction(event -> handleHomeButton());

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

     public void handleReset() {
        if (board.getGameState() == GameState.RUNNING) {

        };
        board.initializeGame(difficulty);
        gameView.resetGame(board.getRemainingMines());
    }

    private void handleHomeButton() {
        if (board.getGameState() == GameState.RUNNING) {

        };
        controller.navigateHome();
    }

    public void handleReveal(Position pos) {
        List<Tile> changed = board.revealTile(pos);
        for (Tile tile : changed) {
            updateTileView(tile);
        }
        if (board.getGameState() == GameState.WON) handleWin();
        if (board.getGameState() == GameState.LOST) handleLoss();
    }

    private void handleWin() {
        updateAllTilesView();
        String formattedTime = TimeFormatter.formatReadable(board.getElapsedSeconds());
        PopupConfig winConfig = PopupFactory.createWinConfig(
                formattedTime,
                this::handleReset,
                controller::navigateHome
        );
        gameView.getPopupOverlay().show(winConfig);
        gameView.playConfetti();
    }

    private void handleLoss() {

        PopupConfig lossConfig = PopupFactory.createLoseConfig(
                this::handleReset,
                controller::navigateHome
        );
        gameView.getPopupOverlay().show(lossConfig);

    }

    public void handleFlag(Position pos) {
        Tile tile = board.getTileAt(pos);
        board.toggleFlag(pos);
        gameView.updateFlagPill(board.getRemainingMines());
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

    public void updateAllTilesView() {
        int rows = difficulty.getRows();
        int cols = difficulty.getColumns();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Position pos = new Position(row, col);
                Tile tile = board.getTileAt(pos);
                updateTileView(board.getTileAt(pos));
            }
        }
    }

    private void startTimer() {
        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            board.tickSecond();
            gameView.updateTimerPill(board.getElapsedSeconds());
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void stopTimer() {
        if (timer != null) timer.stop();
    }

    // TODO remove this (DEBUGGING ONLY)
    public void attachDebugKeybinds(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.W) handleWin();
            if (event.getCode() == KeyCode.L) handleLoss();
            if (event.getCode() == KeyCode.Y) gameView.hidePopup();
        });
    }

    public GameState getGameState() {
        return board.getGameState();
    }
}
