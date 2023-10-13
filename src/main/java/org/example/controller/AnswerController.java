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
import org.example.modaldata.UnitLabelData;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.example.Main.customFontForAll;
import static org.example.controller.QuizController.operationCounts;
import static org.example.controller.QuizController.quizzesdata;

public class AnswerController {
    public Label yourAnswer;
    public Label answer;
    public Label yourAnswer2;
    public Label answer2;
    public Label yourAnswer3;

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
            FXMLLoader quiztestloader = new FXMLLoader(getClass().getResource("/quiztest2.fxml"));
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
            currentStage.setWidth(335);
            currentStage.setHeight(540);

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

    public void setExtractedText(List<String> extractedText) {

        if (!extractedText.isEmpty()) {
            String firstElement = extractedText.get(0);
            System.out.println("第一個答案: " + firstElement);
            yourAnswer.setText(firstElement);
            yourAnswer.setFont(customFontForAll);
        }

        if (extractedText.size() >= 2) {
            String secondElement = extractedText.get(1);
            System.out.println("第二個答案: " + secondElement);
            yourAnswer2.setText(secondElement);
            yourAnswer2.setFont(customFontForAll);
        }
//        System.out.println("Current font size: " + yourAnswer.getFont().getSize());
//        yourAnswer.setText(extractedText);
//        yourAnswer.setFont(customFontForAll);
//        System.out.println("New font size: " + yourAnswer.getFont().getSize());
//        yourAnswer2.setText(extractedText);
//        yourAnswer2.setFont(customFontForAll);
//        yourAnswer3.setText(extractedText);
//        yourAnswer3.setFont(customFontForAll);
    }
}