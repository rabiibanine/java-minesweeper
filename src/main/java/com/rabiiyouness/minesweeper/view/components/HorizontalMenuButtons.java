package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.ArrayList;

public class HorizontalMenuButtons {

    private List<Button> buttons = new ArrayList<>();

    public Pane getComponent() {
        HBox horizontalMenuButtons = new HBox();

        horizontalMenuButtons.getStyleClass().add("menu-bottom-bar");

        String[] buttonsLabels = {"PLAY", "LEADERBOARD", "OPTIONS", "CREDITS", "EXIT"};

        for (String s: buttonsLabels) {
            Button button = new Button(s);
            buttons.add(button);
            horizontalMenuButtons.getChildren().add(button);
            if (s.equals("PLAY")) {
                button.getStyleClass().add("nav-button-primary");
            } else {
                button.getStyleClass().add("nav-button");
            }
        }

        return horizontalMenuButtons;
    }


}
