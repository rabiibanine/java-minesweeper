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

        String[] buttonsLabels = {"Play", "Leaderboard", "Options", "Credits", "Exit"};

        for (String s: buttonsLabels) {
            Button button = new Button(s);
            horizontalMenuButtons.getChildren().add(button);
            buttons.add(button);
        }

        return horizontalMenuButtons;
    }


}
