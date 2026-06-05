package com.rabiiyouness.minesweeper.view.components;

import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.kordamp.ikonli.javafx.FontIcon;

public class PopupOverlay {

    private StackPane root;
    private VBox popupWindow;

    // UI Elements
    private StackPane header;
    private FontIcon closeIcon;
    private Label titleLabel;
    private Label messageLabel;
    private TextField nameInput;
    private HBox buttonLayout;

    public PopupOverlay() {
        // 1. The Root Full-Screen Dimming Overlay
        root = new StackPane();
        root.getStyleClass().add("popup-overlay"); // The dark background

        // 2. The Popup Card Shell (using your exact VBox settings)
        popupWindow = new VBox(20);
        popupWindow.getStyleClass().add("popup-window");
        popupWindow.setAlignment(Pos.CENTER);

        popupWindow.setMaxHeight(Region.USE_PREF_SIZE);

        // --- Assembling your specific layout elements ---

        // Header with Close Button
        closeIcon = new FontIcon("ci-close");
        closeIcon.getStyleClass().add("popup-close-icon");
        closeIcon.setCache(true);
        closeIcon.setCacheHint(CacheHint.SPEED);

        Button closeButton = new Button();
        closeButton.getStyleClass().add("popup-close-button");
        closeButton.setGraphic(closeIcon);

        header = new StackPane(closeButton);
        StackPane.setAlignment(closeButton, Pos.CENTER_RIGHT);

        // Bonus: Make the close icon actually close the popup automatically!
        closeIcon.setOnMouseClicked(e -> this.hide());

        // Title Label
        titleLabel = new Label();
        titleLabel.getStyleClass().add("popup-title");
        titleLabel.setCache(true);
        titleLabel.setCacheHint(CacheHint.QUALITY);

        // Message Label
        messageLabel = new Label();
        messageLabel.getStyleClass().add("popup-message");
        messageLabel.setCache(true);
        messageLabel.setCacheHint(CacheHint.QUALITY);

        // Name Field
        nameInput = new TextField();
        nameInput.setPromptText("Enter your name...");
        nameInput.getStyleClass().add("popup-name-input");
        nameInput.setCache(true);
        nameInput.setCacheHint(CacheHint.QUALITY);
        nameInput.setVisible(false);

        // Button Layout
        buttonLayout = new HBox(15);
        buttonLayout.setAlignment(Pos.CENTER);

        // Add everything to the card in your exact order
        popupWindow.getChildren().addAll(header, titleLabel, messageLabel, nameInput, buttonLayout);

        // Add the card to the overlay and hide it initially
        root.getChildren().add(popupWindow);
        root.setVisible(false);
    }

    /**
     * Reads the config blueprint and updates the UI instantly.
     */
    public void show(PopupConfig config) {
        // 1. Update text
        titleLabel.setText(config.title());
        messageLabel.setText(config.message());
        if (config.title().equals("Victory!")) {
            nameInput.setVisible(true);
            nameInput.setManaged(true);
        }
        else {
            nameInput.setVisible(false);
            nameInput.setManaged(false);
        };

        // 2. Clear old buttons
        buttonLayout.getChildren().clear();

        // 3. Generate buttons based on the Config
        for (PopupAction action : config.actions()) {
            Button btn = new Button(action.buttonText());

            // Apply your specific CSS classes based on the boolean!
            if (action.isPrimary()) {
                btn.getStyleClass().add("popup-button-primary");
            } else {
                btn.getStyleClass().add("popup-button-secondary");
            }

            // Wire the action and make it auto-close the popup
            btn.setOnAction(e -> {
                action.actionToRun().run();
                this.hide();
            });

            buttonLayout.getChildren().add(btn);
        }

        root.setVisible(true);
    }

    public void hide() {
        root.setVisible(false);
    }

    public StackPane getComponent() {
        return root;
    }

    public String getName() {
        return nameInput.getText().trim();
    }
}