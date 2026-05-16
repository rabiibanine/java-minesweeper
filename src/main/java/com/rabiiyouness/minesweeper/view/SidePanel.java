package com.rabiiyouness.minesweeper.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class SidePanel extends VBox {

    private final Label statusLabel = new Label("Ready");

    public SidePanel() {

        super(12);

        getStyleClass().add("side-panel");

        setPrefWidth(250);

        Label title = new Label("Minesweeper");
        title.getStyleClass().add("title");

        Label controls = new Label(
                "Rules Left click reveals. Right click flags. Click a revealed number to chord."
        );

        controls.getStyleClass().add("help-text");
        controls.setWrapText(true);

        statusLabel.getStyleClass().add("section-title");

        getChildren().addAll(title, statusLabel, controls);
    }

    public Label getStatusLabel() {
        return statusLabel;
    }
}