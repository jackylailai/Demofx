package org.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.vo.Quiz;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.example.Main.customFontForAll;
import static org.example.controller.AnswerController.correctAnswerCount;

public class QuizTestController {
    public TextArea question1;
    public TextArea question2;
    public static List<Quiz> quizzes;
    public TextField answer2;
    public TextField answer1;

    public void setQuizTests(List<Quiz> quizzesdata) {
        if (quizzesdata != null) {
            quizzes=quizzesdata;
            System.out.println("傳進unit頁面"+quizzes);
            Quiz quiz1 = quizzesdata.get(0);
            if (quiz1.getTofQuiz() == 1) {
                question1.setText(quiz1.getContent());
                question1.setFont(Font.font(customFontForAll.getFamily(), 18));
                question1.setWrapText(true);
                System.out.println("是非"+quiz1.getTofQuiz());

            } else if (quiz1.getTofQuiz() == 2) {
                question1.setText(quiz1.getContent());
                question1.setFont(Font.font(customFontForAll.getFamily(), 18));
                question1.setWrapText(true);
                System.out.println(quiz1.getTofQuiz());
            }

            Quiz quiz2 = quizzesdata.get(1);
            if (quiz2.getTofQuiz() == 1) {

                question2.setText(quiz2.getContent());
                question2.setFont(Font.font(customFontForAll.getFamily(), 18));
                question2.setWrapText(true);

            } else if (quiz2.getEssayQuiz() == 1) {
                question2.setText("問答題:"+quiz2.getContent());
                question2.setFont(Font.font(customFontForAll.getFamily(), 18));
                question2.setWrapText(true);
            }
        }
    }

    public void handleNextButton(MouseEvent actionEvent) throws IOException {
        String answerFirst = answer1.getText();
        String answerSecond = answer2.getText();
        Quiz quiz1 = quizzes.get(0);
        String correctAnswer1 = quiz1.getAnswer();
        Quiz quiz2 = quizzes.get(1);
        String correctAnswer2 = quiz2.getAnswer();

        System.out.println("quiz1+2"+quiz1+quiz2);
        boolean isAnswer1Correct = answerFirst.equals(correctAnswer1);

        boolean isAnswer2Correct = answerSecond.equals(correctAnswer2);
        System.out.println("答案1是否正确: " + isAnswer1Correct);
        System.out.println("答案2是否正确: " + isAnswer2Correct);
        if (isAnswer1Correct && isAnswer2Correct){
            correctAnswerCount+=2;
        } else if (isAnswer1Correct || isAnswer2Correct) {
            correctAnswerCount++;
        }else{
            System.out.println("問答題全錯");
        }
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
