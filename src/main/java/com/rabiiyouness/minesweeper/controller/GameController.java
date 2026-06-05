package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.dao.DbConnection;
import com.rabiiyouness.minesweeper.dao.ScoreDao;
import com.rabiiyouness.minesweeper.model.Board;
import com.rabiiyouness.minesweeper.model.Position;
import com.rabiiyouness.minesweeper.model.Score;
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
import javafx.concurrent.Task;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.util.Duration;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
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
        gameView = new GameView(difficulty);

        // Initialize pills
        gameView.updateFlagPill(board.getRemainingMines());

        // Start timer
        startTimer();

        bindStaticEvents();
        bindGridEvents();
    }

    public GameView getView(){
        return gameView;
    };

    public Parent getRoot() {
        return gameView.getRoot();
    }

    public void bindStaticEvents() {
        // Reset Button
        gameView.getFaceButton().setOnAction(event -> handleReset());

        // Home Button
        gameView.getHomeButton().setOnAction(event -> handleHome());

        // Settings Button
        gameView.getSettingsButton().setOnAction(event -> handleSettings());
    }

    public void bindGridEvents() {
        TileButton[][] tileButtons = gameView.getTileButtons();

        // Board Tiles
        for (int row = 0; row < tileButtons.length ; row++) {
            for (int col = 0; col < tileButtons[row].length ; col++) {
                Position pos = new Position(row, col);
                TileButton tileButton = tileButtons[row][col];

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
            PopupConfig resetConfig = PopupFactory.createResetConfig(this::restart);
            gameView.getPopupOverlay().show(resetConfig);
            return;
        };
        restart();
    }

    private void handleHome() {
        if (board.getGameState() == GameState.RUNNING) {
            PopupConfig confirmConfig = PopupFactory.createConfirmConfig(() -> {
                controller.navigateHome();
                restart();
            });
            gameView.getPopupOverlay().show(confirmConfig);
            return;
        };
        controller.navigateHome();
    }

    private void handleSettings() {
    PopupConfig settingsConfig = PopupFactory.createSettingsConfig(
            () -> changeDifficultyAndRestart(Difficulty.EXPERT),
            () -> changeDifficultyAndRestart(Difficulty.INTERMEDIATE),
            () -> changeDifficultyAndRestart(Difficulty.BEGINNER)
    );
        gameView.getPopupOverlay().show(settingsConfig);
    }

    public void restart() {
        board.initializeGame(difficulty);
        gameView.changeDifficulty(difficulty);
        bindGridEvents();
    }

    private void changeDifficultyAndRestart(Difficulty newDifficulty) {
        this.difficulty = newDifficulty;
        board.initializeGame(this.difficulty);
        gameView.changeDifficulty(this.difficulty);
        bindGridEvents();
    }

    public void handleReveal(Position pos) {
        List<Tile> changed = board.revealTile(pos);
        updateAllTilesView();
        gameView.setNeutralFace();
        if (board.getGameState() == GameState.WON) handleWin();
        if (board.getGameState() == GameState.LOST) handleLoss();
    }

    public void handleFlag(Position pos) {
        Tile tile = board.getTileAt(pos);
        board.toggleFlag(pos);
        gameView.updateFlagPill(board.getRemainingMines());
        gameView.setWinkFace();
        updateTileView(tile);
    }

    private void handleWin() {
        updateAllTilesView();
        String formattedTime = TimeFormatter.formatReadable(board.getElapsedSeconds());
        PopupConfig winConfig = PopupFactory.createWinConfig(
                formattedTime,
                this::restart,
                this::handleSave
        );
        gameView.getPopupOverlay().show(winConfig);
        gameView.playConfetti();
        gameView.setCoolFace();
    }

    private void handleLoss() {

        PopupConfig lossConfig = PopupFactory.createLoseConfig(
                this::restart,
                () -> {
                    controller.navigateHome();
                    restart();
                }
        );
        gameView.getPopupOverlay().show(lossConfig);
        gameView.setDizzyFace();
    }

    private void handleSave() {

        Task<Void> saveTask = new Task<Void>(){

            @Override
            protected Void call() throws Exception {

                try (Connection connection = DbConnection.getConnection();) {

                    String name = gameView.getPopupOverlay().getName();
                    ScoreDao scoreDao = new ScoreDao(connection);
                    Score score = new Score(name, board.getDifficulty(), board.getElapsedSeconds(), LocalDateTime.now());
                    scoreDao.save(score);
                    return null;

                } catch (SQLException e) {
                    throw new Exception("Database error: " + e.getMessage(), e);
                }
            }

        };

        saveTask.setOnSucceeded(e -> {
            System.out.println("Saved successfully!");
        });

        saveTask.setOnFailed(e -> {
            System.out.println("Failed to save. Try again.");
            System.out.println(saveTask.getException().getMessage());
        });

        new Thread(saveTask).start();
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
            if (event.getCode() == KeyCode.W) {
                board.autoFlagRemainingMines();
                updateAllTilesView();
            }
            if (event.getCode() == KeyCode.E) {
                board.autoReveal();
                updateAllTilesView();
                handleWin();
            }
        });
    }

    public GameState getGameState() {
        return board.getGameState();
    }
}
