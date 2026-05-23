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
        menuView.getPlayButton().setOnAction(event -> controller.handlePlayButton());
        // Exit event
        menuView.getExitButton().setOnAction(event -> Platform.exit());
    }
}
