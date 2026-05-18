package com.rabiiyouness.minesweeper.view;

import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.model.Score;
import com.rabiiyouness.minesweeper.util.TimeFormatter;

import com.rabiiyouness.minesweeper.view.components.DifficultySelector;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.util.List;

public class LeaderboardView extends VBox {
    private final ComboBox<Difficulty> filter = new DifficultySelector();
    private final TableView<Score> table = new TableView<>();

    public LeaderboardView() {
        getStyleClass().add("panel");
        setSpacing(8);
        Label title = new Label("Leaderboard");
        title.getStyleClass().add("section-title");
        filter.getItems().setAll(Difficulty.values());
        filter.setValue(Difficulty.BEGINNER);

        TableColumn<Score, String> player = new TableColumn<>("Player");
        player.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getId())
                )
        );
        TableColumn<Score, String> time = new TableColumn<>("Time");
        time.setCellValueFactory(data ->
                new SimpleStringProperty(
                        TimeFormatter.formatReadable(data.getValue().getCompletionTimeSeconds())
                )
        );
        TableColumn<Score, String> date = new TableColumn<>("Date");
        date.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getPlayedAt().toLocalDate().toString()
                )
        );
        table.getColumns().addAll(player, time, date);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        table.setPrefHeight(190);
        getChildren().addAll(title, filter, table);
    }

    public void setScores(List<Score> scores) {
        table.setItems(FXCollections.observableArrayList(scores));
    }

    public ComboBox<Difficulty> getFilter() { return filter; }
}
