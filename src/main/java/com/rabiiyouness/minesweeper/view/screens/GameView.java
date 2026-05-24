package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.view.components.GameBoard;
import com.rabiiyouness.minesweeper.view.components.GameTopBar;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameView {

    private BorderPane root;
    private GameTopBar gameTopBar;

    public GameView() {
        gameTopBar = new GameTopBar();
        GameBoard gameBoard = new GameBoard();

        root = new BorderPane();
        root.setTop(gameTopBar.getComponent());
        root.setCenter(gameBoard.getComponent());

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
}
