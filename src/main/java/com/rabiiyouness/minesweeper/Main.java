// ==========================
// Main.java
// ==========================
package com.rabiiyouness.minesweeper;

import com.rabiiyouness.minesweeper.controller.MainController;
import com.rabiiyouness.minesweeper.view.screens.MenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MainController mainController = new MainController(stage);
        mainController.init();
    }


    public static void main(String[] args) {
        launch(args);
    }
}