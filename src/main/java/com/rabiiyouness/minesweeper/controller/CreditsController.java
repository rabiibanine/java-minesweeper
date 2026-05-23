package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.view.screens.CreditsView;
import javafx.scene.Parent;

public class CreditsController {

    private MainController controller;
    private CreditsView creditsView;

    public CreditsController(MainController controller) {
        this.controller = controller;
        this.creditsView = new CreditsView();
    }

    public CreditsView getView(){
        return creditsView;
    };

    public Parent getRoot() {
        return creditsView.getRoot();
    }
}
