package com.rabiiyouness.minesweeper.view.components;

import com.rabiiyouness.minesweeper.model.Score;
import com.rabiiyouness.minesweeper.util.TimeFormatter;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * A reusable component that renders a ranked score list for one difficulty.
 * Used inside each tab of the LeaderboardView.
 */
public class LeaderboardTable {

    private final VBox component;

    public LeaderboardTable() {
        component = new VBox();
        component.getStyleClass().add("score-table");
        component.setAlignment(Pos.TOP_CENTER);

        renderEmpty();
    }

    // ── Public API ───────────────────────────────────────────────────────

    public VBox getComponent() {
        return component;
    }

    /**
     * Populates the table with a list of scores.
     * Expects scores already sorted best-first (lowest time = rank 1).
     */
    public void setScores(List<Score> scores) {
        component.getChildren().clear();

        if (scores == null || scores.isEmpty()) {
            renderEmpty();
            return;
        }

        // Header row
        component.getChildren().add(buildHeaderRow());

        // Score rows
        for (int i = 0; i < scores.size(); i++) {
            component.getChildren().add(buildScoreRow(i + 1, scores.get(i)));
        }
    }

    /** Clears all rows and shows the empty-state message. */
    public void clear() {
        component.getChildren().clear();
        renderEmpty();
    }

    public void showError(String message) {
        component.getChildren().clear();
        Label error = new Label(message);
        error.getStyleClass().add("score-error-label");
        error.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        component.getChildren().setAll(error);
    }

    // ── Helpers ──────────────────────────────────────────────────────────

    private void renderEmpty() {
        Label empty = new Label("No scores yet.\nComplete a game to set a record!");
        empty.getStyleClass().add("score-empty-label");
        empty.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        component.getChildren().setAll(empty);
    }

    private HBox buildHeaderRow() {
        Label rank = makeCell("#",    "score-header-cell", "score-cell-rank");
        Label name = makeCell("NAME", "score-header-cell", "score-cell-name");
        Label time = makeCell("TIME", "score-header-cell", "score-cell-time");
        Label date = makeCell("DATE", "score-header-cell", "score-cell-date");

        HBox row = new HBox(rank, name, time, date);
        row.getStyleClass().add("score-header-row");
        return row;
    }

    private HBox buildScoreRow(int rank, Score score) {
        // Medal styling for top 3
        String rankStyle = switch (rank) {
            case 1 -> "score-rank-gold";
            case 2 -> "score-rank-silver";
            case 3 -> "score-rank-bronze";
            default -> "score-rank-default";
        };

        Label rankLabel = makeCell(String.valueOf(rank), "score-data-cell", "score-cell-rank", rankStyle);
        Label nameLabel = makeCell(score.getName(), "score-data-cell", "score-cell-name");
        Label timeLabel = makeCell(TimeFormatter.format(score.getCompletionTimeSeconds()), "score-data-cell", "score-cell-time");
        Label dateLabel = makeCell(score.getFormattedDate(), "score-data-cell", "score-cell-date");

        HBox row = new HBox(rankLabel, nameLabel, timeLabel, dateLabel);
        row.getStyleClass().add("score-data-row");

        // Alternate row shading
        if (rank % 2 == 0) {
            row.getStyleClass().add("score-data-row-even");
        }

        return row;
    }

    /** Creates a Label with the given text and one or more style classes. */
    private Label makeCell(String text, String... styleClasses) {
        Label label = new Label(text);
        label.getStyleClass().addAll(styleClasses);
        HBox.setHgrow(label, Priority.ALWAYS);
        label.setMaxWidth(Double.MAX_VALUE);
        return label;
    }
}