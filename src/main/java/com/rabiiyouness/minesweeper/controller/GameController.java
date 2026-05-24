package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.model.Board;
import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.view.components.GameBoard;
import com.rabiiyouness.minesweeper.view.components.TileButton;
import com.rabiiyouness.minesweeper.view.screens.GameView;
import javafx.scene.Parent;

public class GameController {

    private MainController controller;
    private GameView gameView;

    private Board board;

    public GameController(MainController controller) {
        this.controller = controller;
        board = new Board();
        board.initializeGame(Difficulty.INTERMEDIATE);
        GameBoard gameBoard = new GameBoard(board.getRows(), board.getCols());
        this.gameView = new GameView(gameBoard);

        bindEvents();
    }

    public GameView getView(){
        return gameView;
    };

    public Parent getRoot() {
        return gameView.getRoot();
    }

    public void bindEvents() {
        // Home Button
        gameView.getHomeButton().setOnAction(event -> controller.handleHomeButton());

        // Board Tiles
        for (int row = 0; row < board.getRows() ; row++) {
            for (int col = 0; col < board.getCols() ; col++) {

            }
        }
    }
}
