package org.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Objects;


public class OnlineController {
        public Label timerLabel;
        @FXML
        private Stage stage;

        // 在初始化控制器时设置stage字段
        public void initialize() {
        }
        public void setUnits(Stage currentStage) {
            Duration duration = Duration.seconds(5);
            KeyFrame keyFrame = new KeyFrame(duration, event -> {
                System.out.println("Timer finished. Perform window transition here.");
                openNewOnlineUnitsWindow(currentStage);
            });
            Timeline timeline = new Timeline(keyFrame);
            timeline.setCycleCount(1);
            timeline.play();
        }

    private void openNewOnlineUnitsWindow(Stage currentStage) {
        FXMLLoader onlineUnitsLoader = new FXMLLoader(getClass().getResource("/unit2.fxml"));
        Parent onlineUnitsRoot = null;
        try {
            onlineUnitsRoot = onlineUnitsLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        OnlineUnitsController onlineUnitsController = onlineUnitsLoader.getController();
        Scene onlineUnitsScene = new Scene(onlineUnitsRoot);
        onlineUnitsScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());

        Screen secondScreen = Screen.getScreens().stream()
                .filter(screen -> !screen.equals(Screen.getPrimary()))
                .findFirst()
                .orElse(Screen.getPrimary());

        currentStage.setWidth(1080);
        currentStage.setHeight(960);

        Rectangle2D secondScreenBounds = secondScreen.getVisualBounds();
        double centerX = secondScreenBounds.getMinX() + (secondScreenBounds.getWidth() - 1080) / 2;
        double centerY = secondScreenBounds.getMinY() + (secondScreenBounds.getHeight() - 960) / 2;
        currentStage.setX(centerX);
        currentStage.setY(centerY);
        currentStage.setAlwaysOnTop(true);
        currentStage.setScene(onlineUnitsScene);
        currentStage.setTitle("Online Units");
        System.out.println("Timer finished. Perform window transition here.");
    }
    }
