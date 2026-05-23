package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.view.screens.GameView;
import com.rabiiyouness.minesweeper.view.screens.LeaderboardView;
import javafx.scene.Parent;

public class LeaderboardController {

    private MainController controller;
    private LeaderboardView leaderboardView;

    public LeaderboardController(MainController controller) {
        this.controller = controller;
        this.leaderboardView = new LeaderboardView();
    }

    public LeaderboardView getLeaderboardView() {
        return leaderboardView;
    }

    public Parent getRoot(){
        return leaderboardView.getRoot();
    };
}