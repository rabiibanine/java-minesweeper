package com.rabiiyouness.minesweeper.view.screens;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class OptionsView {

    private BorderPane root;

    public OptionsView() {

        root = new BorderPane();
        root.setCenter(new Label("Options"));
    }

    public Parent getRoot() {
        return root;
    }
}
