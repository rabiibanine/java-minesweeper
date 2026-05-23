package com.rabiiyouness.minesweeper.controller;

import com.rabiiyouness.minesweeper.view.screens.OptionsView;
import javafx.scene.Parent;

public class OptionsController {

    private MainController controller;
    private OptionsView optionsView;

    public OptionsController(MainController controller) {
        this.controller = controller;
        this.optionsView = new OptionsView();
    }

    public OptionsView getView(){
        return optionsView;
    };

    public Parent getRoot() {
        return optionsView.getRoot();
    }
}