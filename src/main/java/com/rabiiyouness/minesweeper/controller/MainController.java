package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.model.enums.GameState;
import javafx.scene.Node;
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


        // TODO remove this (DEBUGGING ONLY)

        gameController.attachDebugKeybinds(scene);

        stage.show();
    }

    private void navigate(Parent newRoot) {
        stage.getScene().setRoot(newRoot);
    }

    public void navigateHome() {
        navigate(menuController.getRoot());
    }

    public void navigateGame() {
        navigate(gameController.getRoot());
    }

    public void navigateLeaderboard() {
        navigate(leaderboardController.getRoot());
    }

    public void navigateOptions() {
        navigate(optionsController.getRoot());
    }

    public void navigateCredits() {
        navigate(creditsController.getRoot());
    }


}
