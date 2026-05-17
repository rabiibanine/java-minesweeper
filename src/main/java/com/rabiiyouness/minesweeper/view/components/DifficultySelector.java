package com.rabiiyouness.minesweeper.view.components;

import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import javafx.scene.control.ComboBox;

public class DifficultySelector extends ComboBox<Difficulty> {
    public DifficultySelector() {
        getItems().setAll(Difficulty.values());
        setValue(Difficulty.BEGINNER);
        setMinWidth(150);
    }
}
