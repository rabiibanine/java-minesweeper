package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.model.Position;
import com.rabiiyouness.minesweeper.view.components.ConfettiOverlay;
import com.rabiiyouness.minesweeper.view.components.GameBoard;
import com.rabiiyouness.minesweeper.view.components.GameTopBar;
import com.rabiiyouness.minesweeper.view.components.TileButton;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameView {

    private final StackPane root;
    private final GameTopBar gameTopBar;
    private final GameBoard gameBoard;
    private final ConfettiOverlay confettiOverlay;

    public GameView(int rows, int cols) {
        gameTopBar = new GameTopBar();
        gameBoard = new GameBoard(rows, cols);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(gameTopBar.getComponent());
        borderPane.setCenter(gameBoard.getComponent());

        confettiOverlay = new ConfettiOverlay();
        root = new StackPane (borderPane, confettiOverlay.getComponent());

        String globalCss = getClass().getResource("/css/global.css").toExternalForm();
        borderPane.getStylesheets().add(globalCss);
        root.getStylesheets().add(globalCss);

        String gameCss = getClass().getResource("/css/game.css").toExternalForm();
        borderPane.getStylesheets().add(gameCss);

    }

    public Parent getRoot() {
        return root;
    }

    public Button getHomeButton() {
        return gameTopBar.getHomeButton();
    }

    public Button getResetButton() {
        return gameTopBar.getResetButton();
    }

    public TileButton[][] getTileButtons() {
        return gameBoard.getTileButtons();
    }

    public TileButton getTileButton(Position pos) {
        return gameBoard.getTileButton(pos);
    }

    public void resetGame(int remainingMines) {
        gameBoard.resetGameBoard();
        gameBoard.getComponent().getStyleClass().setAll("board-container");
        gameTopBar.updateTimerPill(0);
        gameTopBar.updateFlagPill(remainingMines);
    }

    public void updateFlagPill(int remainingMines) {
        gameTopBar.updateFlagPill(remainingMines);
    }

    public void updateTimerPill(int elapsedSeconds) {
        gameTopBar.updateTimerPill(elapsedSeconds);
    }

    public void handleWin() {
        confettiOverlay.play();
        gameBoard.getComponent().getStyleClass().add("board-container-win");
    }
    public void handleLoss() {
        gameBoard.getComponent().getStyleClass().add("board-container-loss");
    }
}
