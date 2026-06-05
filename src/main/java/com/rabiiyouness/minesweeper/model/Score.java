package com.rabiiyouness.minesweeper.model;

import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.util.TimeFormatter;

import java.time.LocalDateTime;



public class Score {
    private static long id_comp = 0;

    private final long id;
    private final String name;
    private final Difficulty difficulty;
    private final int completionTimeSeconds;
    private final LocalDateTime playedAt;

    public Score(String name, Difficulty difficulty, int completionTimeSeconds, LocalDateTime playedAt) {
        this.id = id_comp++;
        this.name = name;
        this.difficulty = difficulty;
        this.completionTimeSeconds = completionTimeSeconds;
        this.playedAt = playedAt;
    }

    public long getId() {return id;}
    public String getName() {return name;}
    public Difficulty getDifficulty() {return difficulty;}
    public int getCompletionTimeSeconds() {return completionTimeSeconds;}
    public LocalDateTime getPlayedAt() {return playedAt;}
    public String getFormattedDate() { return TimeFormatter.format(completionTimeSeconds);}

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", name=" + name +
                ", difficulty=" + difficulty +
                ", completionTimeSeconds=" + completionTimeSeconds +
                ", playedAt=" + playedAt +
                '}';
    }
}
