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

public class QuizTestController {
    public void setQuizTests(List<Quiz> quizzesdata) {
    }

    public void handleNextButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader scorelistloader = new FXMLLoader(getClass().getResource("/scorelist.fxml"));
        Parent scorelistroot = scorelistloader.load();


//        AnswerController scorelistController = scorelistloader.getController();
//            scorelistController.setQuizs(quizzesdata);
        Scene scorelistScene = new Scene(scorelistroot);
        scorelistScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Screen secondScreen = Screen.getScreens().stream()
                .filter(screen -> !screen.equals(Screen.getPrimary()))
                .findFirst()
                .orElse(Screen.getPrimary());

        System.out.println("scorelistScene"+scorelistScene);
        currentStage.setWidth(1080);
        currentStage.setHeight(960);

        Screen screen = Screen.getPrimary();
        Rectangle2D secondScreenBounds = secondScreen.getVisualBounds();
//
        double centerX = secondScreenBounds.getMinX() + (secondScreenBounds.getWidth() - 1080) / 2;
        double centerY = secondScreenBounds.getMinY() + (secondScreenBounds.getHeight() - 960) / 2;
        currentStage.setX(centerX);
        currentStage.setY(centerY);
        currentStage.setAlwaysOnTop(true);
        currentStage.setScene(scorelistScene);
        currentStage.setTitle("ScoreList");
        System.out.println("Timer finished. Perform window transition here.");
    }
}
