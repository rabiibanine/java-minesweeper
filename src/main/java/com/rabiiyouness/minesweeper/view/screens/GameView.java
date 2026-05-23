package com.rabiiyouness.minesweeper.view.screens;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class GameView {
    private BorderPane root;

    public GameView() {

        root = new BorderPane();
        root.setCenter(new Label("Game"));
    }
    public Parent getRoot() {
        return root;
    }
}
