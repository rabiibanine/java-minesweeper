// ==========================
// Main.java
// ==========================
package com.rabiiyouness.minesweeper;

import com.rabiiyouness.minesweeper.controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

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