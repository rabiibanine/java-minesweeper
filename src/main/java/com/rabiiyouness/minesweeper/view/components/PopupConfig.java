package com.rabiiyouness.minesweeper.view.components;

import java.util.List;

// Represents the entire popup's data
public record PopupConfig(String title, String message, List<PopupAction> actions) {}
