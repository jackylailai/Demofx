package org.example.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.http.HttpClientGetData;
import org.example.sikulix.TestDFCS;
import org.example.vo.Oper;
import org.example.vo.Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.example.controller.QuizController.operationCounts;
import static org.example.controller.QuizController.quizzesdata;
import static org.example.netty.server.NettyClient.localhostip;

public class RecordController {
    public Label timerLabel;
    @FXML
    private Stage stage;

    public void initialize() {
    }
    public void setQuizs(List<Quiz> quizzesdata, Stage currentStage) {
        Duration duration = Duration.seconds(5);
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            System.out.println("Timer finished. Perform window transition here.");

            List<String> extractedText = TestDFCS.dfcsmock();
            System.out.println("Extracted Text: " + extractedText);
            if (extractedText == null || extractedText.isEmpty() ) {
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
                System.out.println("判斷extractedText裡面沒東西");
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("錯誤");
                    alert.setHeaderText("發生錯誤");
                    alert.setContentText("發生錯誤，無相關資訊，請勿影響操作並立即通知教官。");

                    alert.showAndWait();
                    String errorText = "錯誤";
                    List<String> errorTextList = new ArrayList<>();
                    errorTextList.add(errorText);
                    openNewWindow(currentStage, errorTextList);
                });
            } else {
                System.out.println("判斷extractedText裡面有東西:"+extractedText);
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
        List<Quiz> quiz=quizzesdata;
        System.out.println(quiz+"資料進來quizzesdata");
        Long unitId = quiz.get(0).getUnitId();
        List<Oper> oper = showOperationDetails(unitId);


        AnswerController answerController = answerloader.getController();
        answerController.setExtractedText(extractedText);


        if (operationCounts == 1) {
            try {
                List<String> answerOper1 = new ArrayList<>();
                answerOper1.add(oper.get(0).getAnswer());
                answerOper1.add(oper.get(1).getAnswer());
                System.out.println("answerOper" + answerOper1.get(0));
                answerController.setAnswerText(answerOper1, extractedText);
            } catch (NullPointerException e) {
                System.err.println("operation為null");
            }
        } else if (operationCounts == 2) {
            try {
                List<String> answerOper2 = new ArrayList<>();
                answerOper2.add(oper.get(2).getAnswer());
                System.out.println("answerOper" + answerOper2.get(0));
                answerController.setAnswerText(answerOper2, extractedText);
            } catch (NullPointerException e) {
                System.err.println("operation為null");
            }
        } else {
            try {
                List<String> answerOper3 = new ArrayList<>();
                answerOper3.add(oper.get(3).getAnswer());
                answerOper3.add(oper.get(4).getAnswer());
                System.out.println("answerOper" + answerOper3.get(0));
                answerController.setAnswerText(answerOper3, extractedText);
            } catch (NullPointerException e) {
                System.err.println("operation為null");
            }
        }
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
    private List<Oper> showOperationDetails(Long unitId) {
//        String baseUrl = "http://"+localhostip+":8080/operation";
        String baseUrl = "http://"+localhostip+":8080/oper/getOperListByUnitId";
        String serverUrl = baseUrl + "/" + unitId;

        String jsonResponse = HttpClientGetData.sendGetRequest(serverUrl);
        if (jsonResponse != null) {
            System.out.println("jsonResponse進入parse前" + jsonResponse);
            List<Oper> oper = (List<Oper>) parseOperationJson(jsonResponse);
            System.out.println("oper parse後" + oper);
            return oper;
        }
        return null;
    }
    private List<Oper> parseOperationJson(String jsonResponse) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonResponse, new TypeReference<List<Oper>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();}
}

//    public void setOperations(List<Quiz> quizzesdata) {
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