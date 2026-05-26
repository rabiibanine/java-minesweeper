package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.dao.DbConnection;
import com.rabiiyouness.minesweeper.dao.ScoreDao;
import com.rabiiyouness.minesweeper.model.Score;
import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.view.screens.LeaderboardView;
import javafx.scene.Parent;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class LeaderboardController {

    private final LeaderboardView leaderboardView;
//    private final ScoreDao scoreDao;
    private final MainController mainController;
//    private final Connection connection;

    public LeaderboardController(MainController mainController) {
//        this.connection = getConnection();
//        this.scoreDao = new ScoreDao(connection);
        this.mainController = mainController;
        this.leaderboardView = new LeaderboardView();

        loadScores();
    }

    private void loadScores() {
        for (Difficulty difficulty : Difficulty.values()) {
//            List<Score> scores = scoreDao.findTop10(difficulty);
//            leaderboardView.populateTable(difficulty, scores);
        }
    }

    public Parent getLeaderboardView() {
        return leaderboardView.getRoot();
    }

    private Connection getConnection() {
        try {
            Connection connection = DbConnection.getConnection();
            return connection;
        } catch (SQLException e) {
            System.out.println("An error occured during DB connection: " + e.getMessage());
            throw new RuntimeException("An error occurred during connection");
        }
    }

    public Parent getRoot() {
        return leaderboardView.getRoot();
    }
}