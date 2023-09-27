package org.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.vo.Quiz;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class QuizController {
    public static List<Quiz> quizzesdata;
    public static Integer operationCounts;
    public Label operationLabel;
    public Text countDownText;
    private int countdown = 60;
    private Timeline timeline;

    public void initialize() {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), this::updateCountdown);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }
    private void updateCountdown(ActionEvent event) {
        if (countdown > 0) {
            countdown--;
            countDownText.setText("倒數:"+countdown+"秒");
        } else {
            operationLabel.setText("時間到");
            timeline.stop();
        }
    }
    public void setQuizs(List<Quiz> quiz) {
        quizzesdata=quiz;
        System.out.println(quiz+"資料進來quiz");

    }

    public void handleButtonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader recordloader = new FXMLLoader(getClass().getResource("/record.fxml"));
        Parent recordroot = recordloader.load();

        RecordController recordController = recordloader.getController();


        Scene recordScene = new Scene(recordroot);
        recordScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        recordController.setQuizs(quizzesdata,currentStage);
        Screen secondScreen = Screen.getScreens().stream()
                .filter(screen -> !screen.equals(Screen.getPrimary()))
                .findFirst()
                .orElse(Screen.getPrimary());

        System.out.println("recordScene"+recordScene);
        currentStage.setWidth(200);
        currentStage.setHeight(300);

        Screen screen = Screen.getPrimary();
        Rectangle2D secondScreenBounds = secondScreen.getBounds();

        double newX = secondScreenBounds.getMaxX() - 200;
        double newY = secondScreenBounds.getMaxY() - 300;
        currentStage.setX(newX);
        currentStage.setY(newY);
        currentStage.setAlwaysOnTop(true);
        currentStage.setScene(recordScene);
        currentStage.setTitle("Record");

    }


    public void setCustomProperty(Integer operation) {
        String operationText = "目前任務 : " + operation;
        operationCounts = operation;
        operationLabel.setText(operationText);
    }
}
