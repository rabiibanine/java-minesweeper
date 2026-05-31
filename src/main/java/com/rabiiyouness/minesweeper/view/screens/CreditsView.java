package com.rabiiyouness.minesweeper.view.screens;

import com.rabiiyouness.minesweeper.view.components.TopBar;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.net.URI;

public class CreditsView {

    private StackPane root;
    private TopBar creditsTopBar;

    public CreditsView() {

        root = new StackPane();

        creditsTopBar = new TopBar();
        VBox creditsCard = getCreditsCard();
        creditsCard.setMaxHeight(Region.USE_PREF_SIZE);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(creditsTopBar.getComponent());
        borderPane.setCenter(creditsCard);

        root.getChildren().setAll(borderPane);

        String globalCss = getClass().getResource("/css/global.css").toExternalForm();
        root.getStylesheets().add(globalCss);

        String creditsCss = getClass().getResource("/css/credits.css").toExternalForm();
        root.getStylesheets().add(creditsCss);
    }
    public Parent getRoot() {
        return root;
    }

    public Button getHomeButton() {
        return creditsTopBar.getHomeButton();
    }

    private VBox getCreditsCard() {
        VBox card = new VBox(16);
        card.getStyleClass().add("credits-card");

        Label title = new Label("Credits");
        title.getStyleClass().add("credits-title");

        Separator sep = new Separator();
        sep.getStyleClass().add("credits-separator");

        String[][] entries = {
                {"Developers", "Rabii Banine, Youness Idblaid"},
                {"Framework", "JavaFX"},
                {"Architecture", "MVC"},
                {"Version", "1.0.0"}
        };

        VBox rows = new VBox(10);
        rows.getStyleClass().add("credits-rows");

        for (String[] entry : entries) {
            HBox row = new HBox();
            row.getStyleClass().add("credits-row");

            Label key = new Label(entry[0]);
            key.getStyleClass().add("credits-key");

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            Label value = new Label(entry[1]);
            value.getStyleClass().add("credits-value");

            row.getChildren().addAll(key, spacer, value);
            rows.getChildren().add(row);
        }

        Separator sep2 = new Separator();
        sep2.getStyleClass().add("credits-separator");

        Hyperlink repoLink = new Hyperlink("github.com/rabiibanine/java-minesweeper");
        repoLink.getStyleClass().add("credits-repo-link");
        repoLink.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/rabiibanine/java-minesweeper"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        FontIcon githubIcon = new FontIcon("ci-logo-github");
        githubIcon.getStyleClass().add("credits-repo-icon");

        HBox repoRow = new HBox(8);
        repoRow.getStyleClass().add("credits-repo-row");
        repoRow.getChildren().addAll(githubIcon, repoLink);

        card.getChildren().addAll(title, sep, rows, sep2, repoRow);
        return card;
    }
}
