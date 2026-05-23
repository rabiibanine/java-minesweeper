package com.rabiiyouness.minesweeper.view.screens;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class LeaderboardView {

    private BorderPane root;

    public LeaderboardView () {
        root = new BorderPane();
        root.setCenter(new Label("Game"));
    }

    public Parent getRoot() {
        return root;
    }
}
