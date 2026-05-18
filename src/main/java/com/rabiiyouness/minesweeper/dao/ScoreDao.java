package com.rabiiyouness.minesweeper.dao;

import com.rabiiyouness.minesweeper.model.Score;
import com.rabiiyouness.minesweeper.model.enums.Difficulty;

import java.sql.*;
import java.util.*;

public class ScoreDao{

    private final Connection connection;

    public ScoreDao(Connection connection) {
        this.connection = connection;
    }


    public void save(Score score) {

        String sql = """
                INSERT INTO scores(
                    difficulty,
                    completion_time_seconds,
                    played_at
                )
                VALUES (?, ?, ?)
                """;

        try (PreparedStatement stmt =
                     connection.prepareStatement(sql)) {

            stmt.setString(1, score.getDifficulty().name());
            stmt.setInt(2, score.getCompletionTimeSeconds());
            stmt.setTimestamp(
                    3,
                    Timestamp.valueOf(score.getPlayedAt())
            );

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Score> findTop10(Difficulty difficulty) {

        List<Score> scores = new ArrayList<>();

        String sql = """
            SELECT *
            FROM scores
            WHERE difficulty = ?
            ORDER BY completion_time_seconds ASC
            LIMIT 10
            """;

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, difficulty.name());

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Score score = new Score(
                        Difficulty.valueOf(
                                rs.getString("difficulty")
                        ),
                        rs.getInt("completion_time_seconds"),
                        rs.getTimestamp("played_at")
                                .toLocalDateTime()
                );

                scores.add(score);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return scores;
    }

}