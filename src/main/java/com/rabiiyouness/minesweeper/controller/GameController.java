package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.view.screens.GameView;
import javafx.scene.Parent;

public class GameController {

    private MainController controller;
    private GameView gameView;

    public GameController(MainController controller) {
        this.controller = controller;
        this.gameView = new GameView();
    }

    public GameView getView(){
        return gameView;
    };

    public Parent getRoot() {
        return gameView.getRoot();
    }
}
