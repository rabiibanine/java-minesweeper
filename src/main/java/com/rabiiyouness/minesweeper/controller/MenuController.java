package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.view.screens.MenuView;
import javafx.application.Platform;
import javafx.scene.Parent;

public class MenuController {

    MenuView menuView;
    MainController controller;

    public MenuController(MainController controller) {
        this.menuView = new MenuView();
        this.controller = controller;
        bindEvents();
    }

    public MenuView getView() {
        return menuView;
    }

    public Parent getRoot() {
        return menuView.getRoot();
    }

    public void bindEvents() {
        // Play event
        menuView.getPlayButton().setOnAction(event -> handlePlayButton());
        // Leaderboard event
        menuView.getLeaderboardButton().setOnAction(event -> handleLeaderboardButton());
        // Options event
        menuView.getOptionsButton().setOnAction(event -> handleOptionsButton());
        // Credits event
        menuView.getCreditsButton().setOnAction(event -> handleCreditsButton());
        // Exit event
        menuView.getExitButton().setOnAction(event -> handleExitButton());
    }

    private void handlePlayButton() {
        controller.navigateGame();
    }

    private void handleLeaderboardButton() {
        controller.navigateLeaderboard();
    }

    private void handleOptionsButton() {
        controller.navigateOptions();
    }

    private void handleCreditsButton() {
        controller.navigateCredits();
    }

    private void handleExitButton() {
        Platform.exit();
    }
}
