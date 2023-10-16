package org.example.controller;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.sikulix.TestDFCS;
import org.example.vo.Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecordController {
    public Label timerLabel;
    @FXML
    private Stage stage;

    // 在初始化控制器时设置stage字段
    public void initialize() {
    }
    public void setQuizs(List<Quiz> quizzesdata, Stage currentStage) {
        Duration duration = Duration.seconds(5);
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            System.out.println("Timer finished. Perform window transition here.");

            List<String> extractedText = TestDFCS.dfcsmock();
            System.out.println("Extracted Text: " + extractedText);
            if (extractedText == null ) {
//                Platform.runLater(() -> {
//
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
//                    Parent loginRoot = null;
//                    try {
//                        loginRoot = loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    LoginController loginController = loader.getController();
//                    loginController.setPrimaryStage(currentStage);
//                    Scene loginScene = new Scene(loginRoot);
//                    System.out.println("es stage"+currentStage);
//                    currentStage.setScene(loginScene);
//                    currentStage.setTitle("Login");
//                });
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("錯誤");
                    alert.setHeaderText("發生錯誤");
                    alert.setContentText("發生錯誤，請勿影響操作並立即通知教官。");

                    alert.showAndWait();
                    String errorText = "錯誤";
                    List<String> errorTextList = new ArrayList<>();
                    errorTextList.add(errorText);
                    openNewWindow(currentStage, errorTextList);
                });
            } else {
                openNewWindow(currentStage, extractedText);
            }
//            openNewWindow(currentStage, extractedText);
        });
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void openNewWindow(Stage stage, List<String> extractedText) {
        FXMLLoader answerloader = new FXMLLoader(getClass().getResource("/answer.fxml"));
        Parent answerroot = null;
        try {
            answerroot = answerloader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        AnswerController answerController = answerloader.getController();
//            answerController.setQuizs(quizzesdata);
        answerController.setExtractedText(extractedText);
        Scene answerScene = new Scene(answerroot);
        answerScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
        Stage currentStage = stage;

        Screen secondScreen = Screen.getScreens().stream()
                .filter(screen -> !screen.equals(Screen.getPrimary()))
                .findFirst()
                .orElse(Screen.getPrimary());

        System.out.println("answerScene"+answerScene);
        currentStage.setWidth(335);
        currentStage.setHeight(425);

        Screen screen = Screen.getPrimary();
        Rectangle2D secondScreenBounds = secondScreen.getVisualBounds();
//
        double centerX = secondScreenBounds.getMinX() + (secondScreenBounds.getWidth() - 335) / 2;
        double centerY = secondScreenBounds.getMinY() + (secondScreenBounds.getHeight() - 425) / 2;
        currentStage.setX(centerX);
        currentStage.setY(centerY);
        currentStage.setAlwaysOnTop(true);
        currentStage.setScene(answerScene);
        currentStage.setTitle("Answer");
        System.out.println("Timer finished. Perform window transition here.");
    }
}
//    public void setQuizs(List<Quiz> quizzesdata) {
//        Duration duration = Duration.seconds(5);
//        KeyFrame keyFrame = new KeyFrame(duration, event -> {
//            FXMLLoader answerloader = new FXMLLoader(getClass().getResource("/answer.fxml"));
//            Parent answerroot = null;
//            try {
//                answerroot = answerloader.load();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            RecordController answerController = answerloader.getController();
////            answerController.setQuizs(quizzesdata);
//
//            Scene answerScene = new Scene(answerroot);
//            answerScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
//            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//            Screen secondScreen = Screen.getScreens().stream()
//                    .filter(screen -> !screen.equals(Screen.getPrimary()))
//                    .findFirst()
//                    .orElse(Screen.getPrimary());
//
//            System.out.println("answerScene"+answerScene);
//            currentStage.setWidth(200);
//            currentStage.setHeight(300);
//
////            Screen screen = Screen.getPrimary();
////            Rectangle2D secondScreenBounds = secondScreen.getBounds();
////
////            double newX = secondScreenBounds.getMaxX() - 200;
////            double newY = secondScreenBounds.getMaxY() - 300;
////            currentStage.setX(newX);
////            currentStage.setY(newY);
//            currentStage.setAlwaysOnTop(true);
//            currentStage.setScene(answerScene);
//            currentStage.setTitle("Score");
//            System.out.println("Timer finished. Perform window transition here.");
//        });
//
//        Timeline timeline = new Timeline(keyFrame);
//        timeline.setCycleCount(1);
//        timeline.play();
//    }