package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.view.components.*;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameView {

    private BorderPane root;

    public GameView () {
//        GameTopBar gameTopBar = new GameTopBar();
//        GameBoard gameBoard = new GameBoard();

        root = new BorderPane();
        // TEMP
        root.setCenter(new Label("hello"));
//        root.setTop(gameTopBar);
//        root.setCenter(gameBoard);
    }

    public Parent getRoot() {
        return root;
    }
}
