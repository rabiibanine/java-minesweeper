package com.rabiiyouness.minesweeper.view.components;

import javafx.animation.*;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class ConfettiOverlay {
    private final Pane root;
    private final Random random = new Random();

    // confetti colors matching your theme
    private final String[] COLORS = {
            "#c87020", "#00bfff", "#ffffff", "#f1c40f", "#1abc9c"
    };

    public ConfettiOverlay() {
        root = new Pane();
        root.setMouseTransparent(true); // clicks pass through
    }

    public void play() {
        for (int i = 0; i < 80; i++) {
            int delay = random.nextInt(2000); // stagger spawning
            PauseTransition pause = new PauseTransition(Duration.millis(delay));
            pause.setOnFinished(e -> spawnParticle());
            pause.play();
        }
    }

    private void spawnParticle() {
        double width = root.getWidth();
        double height = root.getHeight();

        Rectangle particle = new Rectangle(6, 10);
        particle.setFill(Color.web(COLORS[random.nextInt(COLORS.length)]));
        particle.setRotate(random.nextDouble() * 360);

        // start at random x along the bottom
        double startX = random.nextDouble() * width;
        particle.setTranslateX(startX);
        particle.setTranslateY(height);

        root.getChildren().add(particle);

        // rise up with rotation
        TranslateTransition rise = new TranslateTransition(
                Duration.millis(1500 + random.nextInt(1000)), particle
        );
        rise.setToY(-(root.getPrefHeight() * 0.8 + random.nextDouble() * 200));
        rise.setToX(startX + (random.nextDouble() - 0.5) * 200); // drift sideways

        RotateTransition rotate = new RotateTransition(
                Duration.millis(1500), particle
        );
        rotate.setByAngle(random.nextBoolean() ? 360 : -360);
        rotate.setCycleCount(2);

        FadeTransition fade = new FadeTransition(Duration.millis(500), particle);
        fade.setDelay(Duration.millis(1200));
        fade.setToValue(0);
        fade.setOnFinished(e -> root.getChildren().remove(particle)); // cleanup

        new ParallelTransition(rise, rotate, fade).play();
    }

    public Parent getComponent() { return root; }
}