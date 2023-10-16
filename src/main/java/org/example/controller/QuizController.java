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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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
    public Circle secondCircle;
    public Circle thirdCircle;
    public Line secondLine;
    public Line thirdLine;
    public Circle firstCircle;
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
            countDownText.setText(countdown+"秒");
        } else {
//            operationLabel.setText("時間到");
            countDownText.setText("時間到");
            timeline.stop();
        }
    }
    public void setQuizs(List<Quiz> quiz) {
        quizzesdata=quiz;
        System.out.println(quiz+"資料進來quizzesdata");
    }

    public void handleButtonAction(MouseEvent actionEvent) throws IOException {
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
        currentStage.setWidth(335);
        currentStage.setHeight(245);

        Screen screen = Screen.getPrimary();
        Rectangle2D secondScreenBounds = secondScreen.getBounds();

        double newX = secondScreenBounds.getMaxX() - 335;
        double newY = secondScreenBounds.getMaxY() - 245;
        currentStage.setX(newX);
        currentStage.setY(newY);
        currentStage.setAlwaysOnTop(true);
        currentStage.setScene(recordScene);
        currentStage.setTitle("Record");

    }


    public void setCustomProperty(Integer operation) {
        String operationText = "目前任務 : " + operation;
        operationCounts = operation;
        if(operation == 1){
            operationLabel.setText(operationText);
        } else if (operation==2) {
            operationLabel.setText(operationText);
//            Line.setStroke(Color.web("#fffdfd"));
            //線要用stroke 圈用storke+fill
            firstCircle.setStroke(Color.web("#87ff18"));
            firstCircle.setFill(Color.web("#87ff18"));
            secondLine.setStroke(Color.web("#87ff18"));
            secondCircle.setFill(Color.web("DARKGRAY"));
            secondCircle.setStroke(Color.web("DARKGRAY"));
            thirdLine.setStroke(Color.web("#fffdfd"));
            thirdCircle.setFill(Color.web("#fffdfd"));
            thirdCircle.setStroke(Color.web("#fffdfd"));
        }
        else {
            firstCircle.setStroke(Color.web("#87ff18"));
            firstCircle.setFill(Color.web("#87ff18"));
            secondLine.setStroke(Color.web("#87ff18"));
            secondCircle.setFill(Color.web("#87ff18"));
            secondCircle.setStroke(Color.web("#87ff18"));
            thirdLine.setStroke(Color.web("#87ff18"));
            thirdCircle.setFill(Color.web("DARKGRAY"));
            thirdCircle.setStroke(Color.web("DARKGRAY"));
            operationLabel.setText(operationText);
        }
    }
}
