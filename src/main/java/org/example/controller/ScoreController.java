package org.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static org.example.controller.TsController.jsonNodeForUser;

public class ScoreController {
    public void handleGoBackButtonAction(MouseEvent mouseEvent) {
        try {
            FXMLLoader scorelistloader = new FXMLLoader(getClass().getResource("/scorelist.fxml"));
            Parent scorelistroot = scorelistloader.load();


            Scene scorelistScene = new Scene(scorelistroot);
            scorelistScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            Screen secondScreen = Screen.getScreens().stream()
                    .filter(screen -> !screen.equals(Screen.getPrimary()))
                    .findFirst()
                    .orElse(Screen.getPrimary());

            System.out.println("scorelistScene" + scorelistScene);


            Screen screen = Screen.getPrimary();
            Rectangle2D secondScreenBounds = secondScreen.getVisualBounds();

            currentStage.setAlwaysOnTop(true);
            currentStage.setScene(scorelistScene);
            currentStage.setTitle("ScoreList");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
