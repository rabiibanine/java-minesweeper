package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.view.components.HorizontalMenuButtons;
import com.rabiiyouness.minesweeper.view.components.MenuLogo;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class MenuView {

    public Pane getView() {
        BorderPane menuView = new BorderPane();

        MenuLogo menuLogo = new MenuLogo();

        HorizontalMenuButtons horizontalMenuButtons = new HorizontalMenuButtons();

        menuView.setCenter(menuLogo.getComponent());
        menuView.setBottom(horizontalMenuButtons.getComponent());


        return menuView;

    }

}
