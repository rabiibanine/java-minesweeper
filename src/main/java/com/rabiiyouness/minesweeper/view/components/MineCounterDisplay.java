package com.rabiiyouness.minesweeper.view.components;

import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;

public class MineCounterDisplay extends Label {
    public MineCounterDisplay(IntegerProperty remainingMines) {
        getStyleClass().add("counter-display");
        textProperty().bind(remainingMines.asString());
    }
}
