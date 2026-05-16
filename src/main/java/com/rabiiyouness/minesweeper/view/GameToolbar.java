package com.rabiiyouness.minesweeper.view;

import com.rabiiyouness.minesweeper.view.components.DifficultySelector;
import com.rabiiyouness.minesweeper.view.components.MineCounterDisplay;
import com.rabiiyouness.minesweeper.view.components.TimerDisplay;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class GameToolbar extends HBox {
    private final Button restartButton = new Button("↻");
    private final DifficultySelector difficultySelector = new DifficultySelector();

    public GameToolbar(IntegerProperty seconds, IntegerProperty remainingMines) {
        getStyleClass().add("top-bar");
        setAlignment(Pos.CENTER);
        setSpacing(10);
        TimerDisplay timerDisplay = new TimerDisplay(seconds);
        MineCounterDisplay mineCounterDisplay = new MineCounterDisplay(remainingMines);
        restartButton.setMinWidth(44);
        restartButton.getStyleClass().add("secondary");
        getChildren().addAll(timerDisplay, restartButton, mineCounterDisplay, difficultySelector);
    }

    public Button getRestartButton() { return restartButton; }
    public DifficultySelector getDifficultySelector() { return difficultySelector; }
}
