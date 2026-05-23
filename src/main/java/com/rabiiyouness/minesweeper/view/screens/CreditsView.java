package com.rabiiyouness.minesweeper.view.screens;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class CreditsView {

    private BorderPane root;

    public CreditsView() {

        root = new BorderPane();
        root.setCenter(new Label("Credits"));
    }
    public Parent getRoot() {
        return root;
    }
}
