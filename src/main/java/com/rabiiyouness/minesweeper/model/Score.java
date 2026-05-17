package com.rabiiyouness.minesweeper.model;

import com.rabiiyouness.minesweeper.model.enums.Difficulty;

import java.time.LocalDateTime;

public record Score(long id, Difficulty difficulty, int completionTimeSeconds, LocalDateTime playedAt) {
}
