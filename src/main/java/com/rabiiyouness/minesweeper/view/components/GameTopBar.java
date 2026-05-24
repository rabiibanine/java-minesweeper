package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import org.kordamp.ikonli.javafx.FontIcon;

public class GameTopBar {

    private HBox root;
    private TopBarPill flagPill;
    private TopBarPill timerPill;
    private Button resetButton;
    private Button homeButton;

    public GameTopBar() {
        this.root = new HBox();
        root.setAlignment(Pos.CENTER_LEFT);

        HBox leftSide = buildLeftSide();
        HBox rightSide = buildRightSide();

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        root.getStyleClass().add("topbar");
        root.getChildren().addAll(leftSide, spacer, rightSide);
    }

    public Parent getComponent() {
        return root;
    }

    public HBox buildLeftSide() {
        HBox leftSide = new HBox();

        Label logoLabel = new Label("MINESWEEPER PLUS");
        logoLabel.setMaxHeight(Double.MAX_VALUE);
        logoLabel.getStyleClass().add("topbar-logo");
        logoLabel.setCache(true);
        logoLabel.setCacheHint(CacheHint.QUALITY);

        leftSide.getChildren().addAll(logoLabel);
        return leftSide;
    }

    public HBox buildRightSide() {
        HBox rightSide = new HBox();
        rightSide.getStyleClass().add("topbar-right");

        timerPill = new TopBarPill("ci-timer");
        flagPill = new TopBarPill("ci-flag-filled");

        resetButton = new Button();
        FontIcon resetIcon = new FontIcon("ci-restart");
        resetIcon.setCache(true);
        resetIcon.getStyleClass().add("topbar-icon-button-icon");
        resetButton.setGraphic(resetIcon);
        resetButton.getStyleClass().add("topbar-icon-button");

        homeButton = new Button();
        FontIcon homeIcon = new FontIcon("ci-home");
        homeIcon.setCache(true);
        homeIcon.getStyleClass().add("topbar-icon-button-icon");
        homeButton.setGraphic(homeIcon);
        homeButton.getStyleClass().add("topbar-icon-button");

        rightSide.getChildren().addAll(flagPill.getComponent(), timerPill.getComponent(), resetButton, homeButton);
        return rightSide;
    }

    public Button getHomeButton() {
        return homeButton;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public void updateFlagPill(int remainingMines) {
        flagPill.setValue(String.format("%03d", remainingMines));
    }

    public void updateTimerPill(int elapsedSeconds) {
        timerPill.setValue(String.format("%03d", elapsedSeconds));
    }
}
