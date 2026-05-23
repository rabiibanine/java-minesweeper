package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.view.components.HorizontalMenuButtons;
import com.rabiiyouness.minesweeper.view.components.MenuLogo;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.BorderPane;

public class MenuView {

    private final BorderPane root;
    private final HorizontalMenuButtons horizontalMenuButtons;

    public MenuView() {
        horizontalMenuButtons = new HorizontalMenuButtons();
        MenuLogo menuLogo = new MenuLogo();

        root = new BorderPane();
        root.setCenter(menuLogo.getComponent());
        root.setBottom(horizontalMenuButtons.getComponent());

        String globalCss = getClass().getResource("/css/global.css").toExternalForm();
        root.getStylesheets().add(globalCss);

        String menuCss = getClass().getResource("/css/menu.css").toExternalForm();
        root.getStylesheets().add(menuCss);
    }

    public Parent getRoot() { return root; }

    public Button getPlayButton() {
        return horizontalMenuButtons.getButtons().getFirst();
    }

    public Button getExitButton() {
        return horizontalMenuButtons.getButtons().get(4);
    }

    public Button getLeaderboardButton() {
        return horizontalMenuButtons.getButtons().get(1);
    }
}
