// ==========================
// Main.java
// ==========================
package com.rabiiyouness.minesweeper;

import com.rabiiyouness.minesweeper.view.screens.MenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MenuView menuView = new MenuView();

        Scene scene = new Scene(menuView.getView(), 800, 600);

        String globalCss = getClass().getResource("/css/global.css").toExternalForm();
        scene.getStylesheets().add(globalCss);

        String menuCss = getClass().getResource("/css/menu.css").toExternalForm();
        scene.getStylesheets().add(menuCss);

        stage.setTitle("Minesweeper Plus");
        stage.setScene(scene);

        stage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}