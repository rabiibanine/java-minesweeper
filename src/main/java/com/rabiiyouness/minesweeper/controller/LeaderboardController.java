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

import static com.rabiiyouness.minesweeper.dao.DbConnection.getConnection;

public class LeaderboardController {

    private final LeaderboardView leaderboardView;
    private final MainController controller;

    public LeaderboardController(MainController mainController) {
        this.controller = mainController;
        this.leaderboardView = new LeaderboardView();

        bindStaticEvents();
        loadScores();
    }

    // ── Public API ───────────────────────────────────────────────────────

    public LeaderboardView getView() {
        return leaderboardView;
    }

    public Parent getRoot() {
        return leaderboardView.getRoot();
    }

    // ── Event binding ────────────────────────────────────────────────────

    private void bindStaticEvents() {
        leaderboardView.getHomeButton().setOnAction(event -> controller.navigateHome());
    }

    // ── Data loading ─────────────────────────────────────────────────────

    /**
     * Fetches the top-10 scores for every difficulty and pushes them into
     * the view. Mirrors the pattern used in GameController (bind first,
     * then populate state).
     */
    private void loadScores() {
        try (Connection connection = DbConnection.getConnection()) {
            ScoreDao scoreDao = new ScoreDao(connection);
            for (Difficulty difficulty : Difficulty.values()) {
                List<Score> scores = scoreDao.findTop10(difficulty);
                leaderboardView.populateTable(difficulty, scores);
            }
        } catch (SQLException e) {
            System.out.println("An error has occurred while fetching scores: " + e.getMessage());
            leaderboardView.displayError("Error while fetching the scores :(.");
        }
    }

    /**
     * Forces a fresh reload from the database.
     * Can be called by MainController after a game ends and a new score
     * is saved, so the leaderboard reflects the latest results.
     */
    public void refresh() {
        leaderboardView.clearTables();
        loadScores();
    }

}