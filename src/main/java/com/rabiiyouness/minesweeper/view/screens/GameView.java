package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.model.Position;
import com.rabiiyouness.minesweeper.view.components.*;
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
    private final PopupOverlay popupOverlay;

    public GameView(int rows, int cols) {
        gameTopBar = new GameTopBar();
        gameBoard = new GameBoard(rows, cols);
        confettiOverlay = new ConfettiOverlay();
        popupOverlay = new PopupOverlay();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(gameTopBar.getComponent());
        borderPane.setCenter(gameBoard.getComponent());

        root = new StackPane(borderPane, confettiOverlay.getComponent(), popupOverlay.getComponent());

        String globalCss = getClass().getResource("/css/global.css").toExternalForm();
        root.getStylesheets().add(globalCss);

        String gameCss = getClass().getResource("/css/game.css").toExternalForm();
        root.getStylesheets().add(gameCss);

    }

    public Parent getRoot() {
        return root;
    }

    public Button getHomeButton() {
        return gameTopBar.getHomeButton();
    }

    public Button getFaceButton() {
        return gameTopBar.getFaceButton();
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
        setSmileyFace();
    }

    public void updateFlagPill(int remainingMines) {
        gameTopBar.updateFlagPill(remainingMines);
    }

    public void updateTimerPill(int elapsedSeconds) {
        gameTopBar.updateTimerPill(elapsedSeconds);
    }

    public void playConfetti() {
        confettiOverlay.play();
    }

    public PopupOverlay getPopupOverlay() {
        return popupOverlay;
    }

    public void hidePopup() {
        popupOverlay.hide();
    }

    public void setSmileyFace() {
        gameTopBar.setFace("ci-face-satisfied-filled");
    }

    public void setNeutralFace() {
        gameTopBar.setFace("ci-face-neutral-filled");
    }

    public void setDizzyFace() {
        gameTopBar.setFace("ci-face-dizzy-filled");
    }

    public void setWinkFace() {
        gameTopBar.setFace("ci-face-wink-filled");
    }

    public void setCoolFace() {
        gameTopBar.setFace("ci-face-cool");
    }
}
