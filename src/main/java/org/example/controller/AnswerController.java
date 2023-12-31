package org.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Getter;
import org.example.netty.handler.ClientHandler;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.example.Main.customFontForAll;
import static org.example.controller.QuizController.operationCounts;
import static org.example.controller.QuizController.quizzesdata;
import static org.example.controller.TsController.jsonNodeForUser;
import static org.example.controller.UnitController.onlineControlCounts;
import static org.example.netty.handler.ClientHandler.ctxFromHandler;
import static org.example.netty.handler.ClientHandler.sendMessageToServer;

public class AnswerController {
    public static int correctAnswerCount = 0;
    public Label yourAnswer;
    public Label answer;
    public Label yourAnswer2;
    public Label answer2;
    public Label yourAnswer3;
    public Label answer3;

    public void handleNextQuizButton(MouseEvent event) throws IOException {
        if(onlineControlCounts==0){
            if (operationCounts < 3) {
                FXMLLoader quizloader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
                Parent quizroot = quizloader.load();

                QuizController quizController = quizloader.getController();
                quizController.setQuizs(quizzesdata);
                operationCounts += 1;
                quizController.setCustomProperty(operationCounts);

                Scene quizScene = new Scene(quizroot);
                quizScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Screen secondScreen = Screen.getScreens().stream()
                        .filter(screen -> !screen.equals(Screen.getPrimary()))
                        .findFirst()
                        .orElse(Screen.getPrimary());

                System.out.println("quizScene" + quizScene);
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
            } else {
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

                System.out.println("quizScene" + quizScene);
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
        } else if (onlineControlCounts==1) {
            if (operationCounts < 2) {
                FXMLLoader quizloader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
                Parent quizroot = quizloader.load();

                QuizController quizController = quizloader.getController();
                quizController.setQuizs(quizzesdata);
                operationCounts += 1;
                quizController.setCustomProperty(operationCounts);

                Scene quizScene = new Scene(quizroot);
                quizScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Screen secondScreen = Screen.getScreens().stream()
                        .filter(screen -> !screen.equals(Screen.getPrimary()))
                        .findFirst()
                        .orElse(Screen.getPrimary());

                System.out.println("quizScene" + quizScene);
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
            } else {
                System.out.println("第一個人考完 我來看看Onlinecontrolcounts?應該要是1??????"+onlineControlCounts);
                onlineControlCounts+=1;
                ClientHandler.sendCMD(910203, jsonNodeForUser.get("name").asText());
//                sendMessageToServer("finish",ctxFromHandler);
                //第一個同學考完
                try {
                    FXMLLoader scorelistloader = new FXMLLoader(getClass().getResource("/scorelist.fxml"));
                    Parent scorelistroot = scorelistloader.load();


                    Scene scorelistScene = new Scene(scorelistroot);
                    scorelistScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
                    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    Screen secondScreen = Screen.getScreens().stream()
                            .filter(screen -> !screen.equals(Screen.getPrimary()))
                            .findFirst()
                            .orElse(Screen.getPrimary());

                    System.out.println("scorelistScene" + scorelistScene);
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
                }catch(NullPointerException e){
                    System.out.println("nullpoint");
                }
            }
        }else{
                System.out.println("onlinecontrol 剛判斷為2近來 同學2呈現答案 開始跳轉到問答題 onlinecontrolcounts應該是2???"+onlineControlCounts);
                if (onlineControlCounts==2) {
                    sendMessageToServer("finish", ctxFromHandler);
                    onlineControlCounts += 1;
                    //第二個同學考完
                }
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

                System.out.println("quizScene" + quizScene);
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

    public void setAnswerText(List<String> answerText, List<String> extractedText) {
        if (answerText.size() == 2) {
            answer.setText(answerText.get(0));
            answer.setFont(customFontForAll);
            answer2.setText(answerText.get(1));
            answer2.setFont(customFontForAll);
            String userAnswer1 = extractedText.get(0);

            String correctAnswer1 = answerText.get(0);
            String correctAnswer2 = answerText.get(1);
            if (extractedText.size() > 1) {
                String userAnswer2 = extractedText.get(1);
                if (userAnswer1.equals(correctAnswer1) && userAnswer2.equals(correctAnswer2)) {
                    System.out.println("2題答對");
                    correctAnswerCount += 2;
                } else if (userAnswer1.equals(correctAnswer1) || userAnswer2.equals(correctAnswer2)) {
                    System.out.println("1題答對");
                    correctAnswerCount++;
                } else {
                    System.out.println("全錯");
                }
            }

        } else {
            answer.setText(answerText.get(0));
            answer.setFont(customFontForAll);
            System.out.println("答案一個");
            String userAnswer1 = extractedText.get(0);
            String correctAnswer1 = answerText.get(0);
            if (userAnswer1.equals(correctAnswer1)) {
                System.out.println("2題答對");
                correctAnswerCount++;
            } else {
                System.out.println("全錯");

            }
        }
        System.out.println(correctAnswerCount+"總計答對題數");
    }


}