package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.vo.Quiz;
import org.example.vo.Unit;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class QuizController {
    public static List<Quiz> quizzesdata;

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
}
