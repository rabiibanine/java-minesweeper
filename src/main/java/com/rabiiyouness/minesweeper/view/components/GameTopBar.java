package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class GameTopBar {

    private HBox root;
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

        leftSide.getChildren().addAll(logoLabel);
        return leftSide;
    }

    public HBox buildRightSide() {
        HBox rightSide = new HBox();

        homeButton = new Button();
        ImageView icon = new ImageView(new Image(getClass().getResourceAsStream("/icons/home.png")));
        icon.setFitWidth(36);
        icon.setFitHeight(36);
        homeButton.setGraphic(icon);
        homeButton.getStyleClass().add("topbar-icon-button");

        rightSide.getChildren().addAll(homeButton);
        return rightSide;
    }

    public Button getHomeButton() {
        return homeButton;
    }
}
