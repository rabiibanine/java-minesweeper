// ==========================
// Main.java
// ==========================
package com.rabiiyouness.minesweeper;

import com.rabiiyouness.minesweeper.model.Board;
import com.rabiiyouness.minesweeper.model.enums.Difficulty;
import com.rabiiyouness.minesweeper.view.MainView;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    private Board board;

    @Override
    public void start(Stage stage) {

        /*
        * ce code juste pour le test d'ouvrir une fentere de voir UI
        * */
        SimpleIntegerProperty seconds =
                new SimpleIntegerProperty(0);

        SimpleIntegerProperty remainingMines =
                new SimpleIntegerProperty(0);

        MainView mainView =
                new MainView(seconds, remainingMines);

        // INITIAL DIFFICULTY
        Difficulty difficulty =
                mainView.getToolbar()
                        .getDifficultySelector()
                        .getValue();

        createBoard(mainView, difficulty, remainingMines);

        // LISTEN FOR DIFFICULTY CHANGES
        mainView.getToolbar()
                .getDifficultySelector()
                .valueProperty()
                .addListener((obs, oldDifficulty, newDifficulty) -> {

                    createBoard(
                            mainView,
                            newDifficulty,
                            remainingMines
                    );
                });

        Scene scene = new Scene(mainView, 900, 620);

        scene.getStylesheets().add(
                Objects.requireNonNull(
                        getClass().getResource("/css/styles.css")
                ).toExternalForm()
        );

        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }

    private void createBoard(
            MainView mainView,
            Difficulty difficulty,
            SimpleIntegerProperty remainingMines
    ) {

        board = new Board(
                difficulty.getRows(),
                difficulty.getColumns(),
                difficulty.getMines(),
                difficulty
        );

        remainingMines.set(difficulty.getMines());

        mainView.getBoardView().build(board, (button, event) -> {

            Stage modal = new Stage();

            modal.initModality(Modality.APPLICATION_MODAL);

            modal.setTitle("Tile Clicked");

            Label label = new Label(
                    "You clicked tile at row "
                            + button.getRow()
                            + ", column "
                            + button.getColumn()
            );

            VBox root = new VBox(label);

            root.setSpacing(10);
            root.setStyle("-fx-padding: 20;");

            Scene modalScene = new Scene(root, 300, 150);

            modal.setScene(modalScene);

            modal.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}