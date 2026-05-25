package com.rabiiyouness.minesweeper.util;

import com.rabiiyouness.minesweeper.view.components.PopupAction;
import com.rabiiyouness.minesweeper.view.components.PopupConfig;

import java.util.List;

public class PopupFactory {

    // Generates the Win Blueprint
    public static PopupConfig createWinConfig(String time, Runnable onRestart, Runnable onMenu) {
        return new PopupConfig(
                "Victory!",
                "You cleared the board in " + time + ".",
                List.of(
                        new PopupAction("Play Again", onRestart, true),
                        new PopupAction("Main Menu", onMenu, false)
                )
        );
    }

    // Generates the Lose Blueprint
    public static PopupConfig createLoseConfig(Runnable onRestart, Runnable onMenu) {
        return new PopupConfig(
                "Game Over",
                "You hit a mine!",
                List.of(
                        new PopupAction("Try Again", onRestart, true),
                        new PopupAction("Main Menu", onMenu, false)
                )
        );
    }

    // Generates the Confirm Blueprint
    public static PopupConfig createConfirmConfig(Runnable onLeave) {
        return new PopupConfig(
                "Quit Game?",
                "Your progress will be lost.",
                List.of(
                        new PopupAction("Leave", onLeave, true),
                        new PopupAction("Cancel", () -> {}, false) // Empty runnable, overlay just closes
                )
        );
    }
}