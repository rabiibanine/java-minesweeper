package com.rabiiyouness.minesweeper.view.components;

// Represents a single button's data
public record PopupAction(String buttonText, Runnable actionToRun, boolean isPrimary) {}