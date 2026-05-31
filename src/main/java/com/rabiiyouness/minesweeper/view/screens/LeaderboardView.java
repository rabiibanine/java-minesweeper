package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.model.Score;
import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.view.components.LeaderboardTable;
import com.rabiiyouness.minesweeper.view.components.TopBar;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class LeaderboardView {

    private final StackPane root;
    private final TopBar topBar;
    private final TabPane tabPane;

    private LeaderboardTable beginnerTable;
    private LeaderboardTable intermediateTable;
    private LeaderboardTable expertTable;

    public LeaderboardView() {
        topBar = new TopBar("LEADERBOARD");

        // ── Tab pane (one tab per difficulty) ──────────────────────────
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getStyleClass().add("leaderboard-tab-pane");

        beginnerTable    = new LeaderboardTable();
        intermediateTable = new LeaderboardTable();
        expertTable      = new LeaderboardTable();

        Tab beginnerTab     = buildTab("BEGINNER",     beginnerTable);
        Tab intermediateTab = buildTab("INTERMEDIATE", intermediateTable);
        Tab expertTab       = buildTab("EXPERT",       expertTable);

        tabPane.getTabs().addAll(beginnerTab, intermediateTab, expertTab);
        VBox.setVgrow(tabPane, Priority.ALWAYS);

        // ── Empty-state label shown when no scores exist ────────────────
        VBox contentWrapper = new VBox(tabPane);
        contentWrapper.setAlignment(Pos.CENTER);
        contentWrapper.getStyleClass().add("leaderboard-content-wrapper");

        // ── Root layout ─────────────────────────────────────────────────
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topBar.getComponent());
        borderPane.setCenter(contentWrapper);

        root = new StackPane(borderPane);

        // ── Stylesheets ──────────────────────────────────────────────────
        String globalCss = getClass().getResource("/css/global.css").toExternalForm();
        root.getStylesheets().add(globalCss);

        String leaderboardCss = getClass().getResource("/css/leaderboard.css").toExternalForm();
        root.getStylesheets().add(leaderboardCss);
    }

    // ── Public API ───────────────────────────────────────────────────────

    public Parent getRoot() {
        return root;
    }

    public Button getHomeButton() {
        return topBar.getHomeButton();
    }

    /**
     * Fills the table for the given difficulty with a list of scores.
     * Called by LeaderboardController once data is fetched.
     */
    public void populateTable(Difficulty difficulty, List<Score> scores) {
        switch (difficulty) {
            case BEGINNER     -> beginnerTable.setScores(scores);
            case INTERMEDIATE -> intermediateTable.setScores(scores);
            case EXPERT       -> expertTable.setScores(scores);
        }
    }

    /**
     * Clears all tables (useful for a refresh action).
     */
    public void clearTables() {
        beginnerTable.clear();
        intermediateTable.clear();
        expertTable.clear();
    }


    public void displayError(String message) {
        beginnerTable.showError(message);
        intermediateTable.showError(message);
        expertTable.showError(message);
    }

    // ── Helpers ──────────────────────────────────────────────────────────

    private Tab buildTab(String title, LeaderboardTable table) {
        Label label = new Label(title);
        label.getStyleClass().add("leaderboard-tab-label");

        Tab tab = new Tab();
        tab.setGraphic(label);
        tab.setContent(table.getComponent());
        return tab;
    }
}