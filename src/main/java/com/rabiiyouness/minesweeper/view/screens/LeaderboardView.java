package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.controller.LeaderboardController;
import com.rabiiyouness.minesweeper.model.Score;
import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.util.TimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LeaderboardView {

    private final BorderPane root;
    private final Map<Difficulty, TableView<Score>> tables;

    public LeaderboardView() {
        this.tables = new EnumMap<>(Difficulty.class);
        this.root = new BorderPane();

        buildUI();
    }

    private void buildUI() {
        // Top bar
        // ...

        // Tab pane — one tab per difficulty
        TabPane tabPane = new TabPane();
        for (Difficulty difficulty : Difficulty.values()) {
            TableView<Score> table = buildTable();
            tables.put(difficulty, table);

            Tab tab = new Tab(difficulty.name(), table);
            tab.setClosable(false);
            tabPane.getTabs().add(tab);
        }

        root.setCenter(tabPane);
    }

    private TableView<Score> buildTable() {
        TableView<Score> table = new TableView<>();

        TableColumn<Score, Integer> rankCol = new TableColumn<>("#");
        // rank is just the row index + 1, not stored in DB
        rankCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.valueOf(getIndex() + 1));
            }
        });

        TableColumn<Score, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        TimeFormatter.format(data.getValue().getCompletionTimeSeconds())
                )
        );

        TableColumn<Score, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getPlayedAt().toLocalDate().toString()
                )
        );

        table.getColumns().addAll(rankCol, timeCol, dateCol);
        return table;
    }

    public void populateTable(Difficulty difficulty, List<Score> scores) {
        tables.get(difficulty).setItems(
                FXCollections.observableArrayList(scores)
        );
    }

    public Parent getRoot() { return root; }
}