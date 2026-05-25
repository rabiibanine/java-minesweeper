package com.rabiiyouness.minesweeper.view.components;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;

public class PopupOverlay {
    private StackPane root;
    private VBox popupCard;
    private Label titleLabel;
    private Label messageLabel;
    private HBox buttonLayout;

    public PopupOverlay() {
        // Build the empty shell once
        root = new StackPane();
        root.getStyleClass().add("popup-overlay");

        popupCard = new VBox(20);
        popupCard.getStyleClass().add("popup-window");

        titleLabel = new Label();
        messageLabel = new Label();
        buttonLayout = new HBox(15);

        popupCard.getChildren().addAll(titleLabel, messageLabel, buttonLayout);
        root.getChildren().add(popupCard);
        root.setVisible(false);
    }

    // THE MAGIC METHOD
    public void show(PopupConfig config) {
        // 1. Set the text
        titleLabel.setText(config.title());
        messageLabel.setText(config.message());

        // 2. Clear old buttons
        buttonLayout.getChildren().clear();

        // 3. Generate new buttons strictly based on the blueprint
        for (PopupAction action : config.actions()) {
            Button btn = new Button(action.buttonText());

            // Apply different CSS if it's the primary "highlighted" button
            if (action.isPrimary()) {
                btn.getStyleClass().add("primary-button");
            }

            // Instantly wire the action AND tell the popup to close itself!
            btn.setOnAction(e -> {
                action.actionToRun().run();
                this.hide();
            });

            buttonLayout.getChildren().add(btn);
        }

        root.setVisible(true);
    }

    public void hide() { root.setVisible(false); }
    public StackPane getComponent() { return root; }
}