package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.vo.Quiz;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.example.controller.QuizController.operationCounts;
import static org.example.controller.QuizController.quizzesdata;

public class AnswerController {
    public void handleNextQuizButton(ActionEvent event) throws IOException {
        FXMLLoader quizloader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
        Parent quizroot = quizloader.load();

        QuizController quizController = quizloader.getController();
        quizController.setQuizs(quizzesdata);
        operationCounts+=1;
        quizController.setCustomProperty(operationCounts);

        Scene quizScene = new Scene(quizroot);
        quizScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Screen secondScreen = Screen.getScreens().stream()
                .filter(screen -> !screen.equals(Screen.getPrimary()))
                .findFirst()
                .orElse(Screen.getPrimary());

        System.out.println("quizScene"+quizScene);
        currentStage.setWidth(200);
        currentStage.setHeight(300);

        Screen screen = Screen.getPrimary();
        Rectangle2D secondScreenBounds = secondScreen.getBounds();

        double newX = secondScreenBounds.getMaxX() - 200;
        double newY = secondScreenBounds.getMaxY() - 300;
        currentStage.setX(newX);
        currentStage.setY(newY);
        currentStage.setAlwaysOnTop(true);
        currentStage.setScene(quizScene);
        currentStage.setTitle("Quiz List");
    }
}
