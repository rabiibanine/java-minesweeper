package com.rabiiyouness.minesweeper.view;

import com.rabiiyouness.minesweeper.model.Position;
import com.rabiiyouness.minesweeper.view.components.TileButton;
import com.rabiiyouness.minesweeper.model.Board;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;

public class GameBoardView extends StackPane {
    private final GridPane gridPane = new GridPane();
    private TileButton[][] buttons;

    public GameBoardView() {
        getStyleClass().add("board-wrap");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        getChildren().add(gridPane);
    }

    public void build(Board board, TileClickHandler handler) {
        gridPane.getChildren().clear();
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        buttons = new TileButton[board.getRows()][board.getCols()];
        for (int c = 0; c < board.getCols(); c++) {
            ColumnConstraints constraints = new ColumnConstraints();
            constraints.setHgrow(Priority.ALWAYS);
            constraints.setFillWidth(true);
            constraints.setPercentWidth(100.0 / board.getCols());
            gridPane.getColumnConstraints().add(constraints);
        }
        for (int r = 0; r < board.getRows(); r++) {
            RowConstraints constraints = new RowConstraints();
            constraints.setVgrow(Priority.ALWAYS);
            constraints.setFillHeight(true);
            constraints.setPercentHeight(100.0 / board.getRows());
            gridPane.getRowConstraints().add(constraints);
        }
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                TileButton button = new TileButton(r, c);
                button.render(board.getTileAt(new Position(r, c)));
                button.setOnMouseClicked(event -> handler.handle(button, event));
                buttons[r][c] = button;
                gridPane.add(button, c, r);
            }
        }
    }

    public void render(Board board) {
        for (int r = 0; r < board.getRows(); r++) {
            for (int c = 0; c < board.getCols(); c++) {
                buttons[r][c].render(board.getTileAt(new Position(r, c)));
            }
        }
    }

    public TileButton getButton(int row, int column) {
        return buttons[row][column];
    }

    @FunctionalInterface
    public interface TileClickHandler {
        void handle(TileButton button, MouseEvent event);
    }
}
