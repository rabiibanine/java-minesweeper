package com.rabiiyouness.minesweeper.controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    private Stage stage;
    MenuController menuController;
    GameController gameController;
    LeaderboardController leaderboardController;
    OptionsController optionsController;
    CreditsController creditsController;

    public MainController(Stage stage) {
        this.stage = stage;
        this.menuController = new MenuController(this);
        this.gameController = new GameController(this);
        this.leaderboardController = new LeaderboardController(this);
        this.optionsController = new OptionsController(this);
        this.creditsController = new CreditsController(this);
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
        navigate(gameController.getRoot());
    }

    public void handleLeaderboardButton() {
        navigate(leaderboardController.getRoot());
    }

    public void handleOptionsButton() {
        navigate(optionsController.getRoot());
    }

    public void handleCreditsButton() {
        navigate(creditsController.getRoot());
    }

    public void handleHomeButton() { navigate(menuController.getRoot()); }
}
