package com.rabiiyouness.minesweeper.view.components;

import com.rabiiyouness.minesweeper.util.TimeFormatter;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;

public class TimerDisplay extends Label {
    public TimerDisplay(IntegerProperty seconds) {
        getStyleClass().add("counter-display");
        textProperty().bind(seconds.asString().map(value -> TimeFormatter.format(Integer.parseInt(value))));
    }
}
