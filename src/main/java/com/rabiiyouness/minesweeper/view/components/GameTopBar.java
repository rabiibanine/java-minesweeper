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
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class GameTopBar {

    private StackPane root;
    private TopBarPill flagPill;
    private TopBarPill timerPill;
    private Button faceButton;
    private Button homeButton;
    private Button settingsButton;

    public GameTopBar() {
        this.root = new StackPane();
        root.getStyleClass().add("topbar");

        HBox leftSide = buildLeftSide();
        HBox rightSide = buildRightSide();
        HBox middle = buildMiddle();

        HBox backgroundHBox = new HBox();
        backgroundHBox.setAlignment(Pos.CENTER_LEFT);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        backgroundHBox.getChildren().addAll(leftSide, spacer, rightSide);

        root.getChildren().addAll(backgroundHBox, middle);
        StackPane.setAlignment(middle, Pos.CENTER);
    }

    public Parent getComponent() {
        return root;
    }

    public HBox buildLeftSide() {
        HBox leftSide = new HBox();

        Label logoLabel = new Label("MINESWEEPER");
        logoLabel.setMaxHeight(Double.MAX_VALUE);
        logoLabel.getStyleClass().add("topbar-logo-label");
        logoLabel.setCache(true);
        logoLabel.setCacheHint(CacheHint.QUALITY);

        FontIcon plusIcon = new FontIcon("ci-add");
        plusIcon.getStyleClass().add("topbar-logo-icon");
        plusIcon.setCache(true);
        plusIcon.setCacheHint(CacheHint.SPEED);

        leftSide.getChildren().addAll(logoLabel, plusIcon);
        leftSide.setAlignment(Pos.CENTER);
        return leftSide;
    }

    public HBox buildRightSide() {
        HBox rightSide = new HBox();
        rightSide.getStyleClass().add("topbar-right");

        homeButton = new Button();
        FontIcon homeIcon = new FontIcon("ci-home");
        homeIcon.setCache(true);
        homeIcon.getStyleClass().add("topbar-icon-button-icon");
        homeButton.setGraphic(homeIcon);
        homeButton.getStyleClass().add("topbar-icon-button");

        settingsButton = new Button();
        FontIcon settingsIcon = new FontIcon("ci-settings");
        settingsIcon.setCache(true);
        settingsIcon.getStyleClass().add("topbar-icon-button-icon");
        settingsButton.setGraphic(settingsIcon);
        settingsButton.getStyleClass().add("topbar-icon-button");

        rightSide.getChildren().addAll(homeButton, settingsButton);
        return rightSide;
    }

    public HBox buildMiddle() {
        HBox middle = new HBox(8);

        timerPill = new TopBarPill("ci-timer");
        flagPill = new TopBarPill("ci-flag-filled");

        faceButton = new Button();
        FontIcon faceIcon = new FontIcon("ci-face-satisfied-filled");
        faceIcon.getStyleClass().add("topbar-face-icon");
        faceButton.setGraphic(faceIcon);
        faceButton.getStyleClass().add("topbar-icon-button");

        middle.getChildren().addAll(flagPill.getComponent(), faceButton, timerPill.getComponent());
        middle.setMaxWidth(Region.USE_PREF_SIZE);
        return middle;
    }

    public Button getHomeButton() {
        return homeButton;
    }

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getFaceButton() { return faceButton; }

    public void setFace(String s) {
        FontIcon icon = new FontIcon(s);
        icon.getStyleClass().add("topbar-face-icon");
        faceButton.setGraphic(icon);
    }

    public void updateFlagPill(int remainingMines) {
        flagPill.setValue(String.format("%03d", remainingMines));
    }

    public void updateTimerPill(int elapsedSeconds) {
        timerPill.setValue(String.format("%03d", elapsedSeconds));
    }
}
