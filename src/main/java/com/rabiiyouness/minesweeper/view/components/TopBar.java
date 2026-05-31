package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import org.kordamp.ikonli.javafx.FontIcon;

public class TopBar {


        private StackPane root;
        private Button homeButton;
        private String title;

        public TopBar(String title) {
            this.title = title;

            root = new StackPane();
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

            Label logoLabel = new Label(title);
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

            rightSide.getChildren().addAll(homeButton);
            return rightSide;
        }

        public HBox buildMiddle() {
            HBox middle = new HBox(8);
            middle.setMaxWidth(Region.USE_PREF_SIZE);
            return middle;
        }

        public Button getHomeButton() {
            return homeButton;
        }

}
