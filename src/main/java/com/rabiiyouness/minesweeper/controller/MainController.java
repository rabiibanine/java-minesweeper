package com.rabiiyouness.minesweeper.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    private Stage stage;
    MenuController menuController;
    LeaderboardController gameController;
    LeaderboardController leaderboardController;

    public MainController(Stage stage) {
        this.stage = stage;
        this.menuController = new MenuController(this);
        this.gameController = new LeaderboardController(this);
        this.leaderboardController = new LeaderboardController(this);
    }

    public void init() {
        Scene scene = new Scene(menuController.getRoot(), 800, 600);

        stage.setTitle("MINESWEEPER PLUS");
        stage.setScene(scene);

        stage.show();
    }

    public void navigate(Parent newRoot) {
        stage.getScene().setRoot(newRoot);
    }

    public void handlePlayButton() {
        // TODO
    }
}
