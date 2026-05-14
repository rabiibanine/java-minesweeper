package com.rabiiyouness.minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Create a simple layout and add some text
        StackPane root = new StackPane();
        root.getChildren().add(new Label("JavaFX is working perfectly!"));

        // 2. Put that layout into a Scene (width: 400, height: 300)
        Scene scene = new Scene(root, 400, 300);

        // 3. Configure the Stage (the window) and show it
        primaryStage.setTitle("Minesweeper - Test Build");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}