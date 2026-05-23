package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.view.screens.MenuView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainController {

    private Stage stage;

    public MainController(Stage stage) {
        this.stage = stage;
    }

    public void init() {
        MenuController menuController = new MenuController(this);
        Scene scene = new Scene(menuController.getRoot(), 800, 600);

        stage.setTitle("MINESWEEPER PLUS");
        stage.setScene(scene);

        stage.show();
    }

    public void navigate(Parent newRoot) {
        stage.getScene().setRoot(newRoot);
    }
}
