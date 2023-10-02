package org.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static org.example.controller.QuizController.operationCounts;
import static org.example.controller.QuizController.quizzesdata;

public class AnswerController {
    public Label yourAnswer;
    public Label answer;

    public void handleNextQuizButton(MouseEvent event) throws IOException {
        if( operationCounts < 3){
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
            currentStage.setWidth(335);
            currentStage.setHeight(540);

            Screen screen = Screen.getPrimary();
            Rectangle2D secondScreenBounds = secondScreen.getBounds();

            double newX = secondScreenBounds.getMaxX() - 335;
            double newY = secondScreenBounds.getMaxY() - 540;
            currentStage.setX(newX);
            currentStage.setY(newY);
            currentStage.setAlwaysOnTop(true);
            currentStage.setScene(quizScene);
            currentStage.setTitle("Quiz List");
        }else{
            FXMLLoader quiztestloader = new FXMLLoader(getClass().getResource("/quiztest.fxml"));
            Parent quiztestroot = quiztestloader.load();

            QuizTestController quizTestController = quiztestloader.getController();
            quizTestController.setQuizTests(quizzesdata);
//            operationCounts+=1;
//            quizTestController.setCustomProperty(operationCounts);

            Scene quizScene = new Scene(quiztestroot);
            quizScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Screen secondScreen = Screen.getScreens().stream()
                    .filter(screen -> !screen.equals(Screen.getPrimary()))
                    .findFirst()
                    .orElse(Screen.getPrimary());

            System.out.println("quizScene"+quizScene);
            currentStage.setWidth(600);
            currentStage.setHeight(600);

            Screen screen = Screen.getPrimary();
            Rectangle2D secondScreenBounds = secondScreen.getBounds();

            double centerX = secondScreenBounds.getMinX() + secondScreenBounds.getWidth() / 2;
            double centerY = secondScreenBounds.getMinY() + secondScreenBounds.getHeight() / 2;

            currentStage.setX(centerX - currentStage.getWidth() / 2);
            currentStage.setY(centerY - currentStage.getHeight() / 2);
            currentStage.setAlwaysOnTop(true);
            currentStage.setScene(quizScene);
            currentStage.setTitle("Quiz Test List");
        }
    }

    public void setExtractedText(String extractedText) {
        yourAnswer.setText(extractedText);
    }
}