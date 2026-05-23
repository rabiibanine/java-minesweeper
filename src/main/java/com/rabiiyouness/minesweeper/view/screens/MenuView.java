package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.view.components.HorizontalMenuButtons;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class MenuView {

    public Pane getView() {
        BorderPane menuView = new BorderPane();

        HorizontalMenuButtons horizontalMenuButtons = new HorizontalMenuButtons();

        menuView.setBottom(horizontalMenuButtons.getComponent());


        return menuView;

    }

}
