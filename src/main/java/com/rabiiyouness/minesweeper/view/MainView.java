package com.rabiiyouness.minesweeper.view;

import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;

public class MainView extends ScrollPane {

    private final BorderPane root =
            new BorderPane();

    private final GameToolbar toolbar;

    private final GameBoardView boardView =
            new GameBoardView();

    private final SidePanel sidePanel =
            new SidePanel();

    private final LeaderboardView leaderboardView =
            new LeaderboardView();

    public MainView(
            IntegerProperty seconds,
            IntegerProperty remainingMines
    ) {

        // ROOT LAYOUT
        root.getStyleClass().add("app-shell");

        toolbar =
                new GameToolbar(
                        seconds,
                        remainingMines
                );

        root.setPadding(new Insets(14));

        root.setTop(toolbar);
        root.setCenter(boardView);
        root.setRight(sidePanel);
        root.setBottom(leaderboardView);

        BorderPane.setMargin(
                leaderboardView,
                new Insets(12, 0, 0, 0)
        );

        BorderPane.setMargin(
                toolbar,
                new Insets(0, 0, 12, 0)
        );

        BorderPane.setMargin(
                boardView,
                new Insets(0, 12, 0, 0)
        );

        // SCROLLPANE CONFIG
        setContent(root);

        setFitToWidth(true);
        setFitToHeight(true);

        setPannable(true);

        setHbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );

        setVbarPolicy(
                ScrollPane.ScrollBarPolicy.AS_NEEDED
        );
    }

    public GameToolbar getToolbar() {
        return toolbar;
    }

    public GameBoardView getBoardView() {
        return boardView;
    }

    public LeaderboardView getLeaderboardView() {
        return leaderboardView;
    }

    public Label getStatusLabel() {
        return sidePanel.getStatusLabel();
    }
}