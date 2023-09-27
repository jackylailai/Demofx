package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.http.HttpClientGetData;
import org.example.modaldata.UnitLabelData;
import org.example.vo.Quiz;
import org.example.vo.Unit;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.example.controller.UnitListController.unitsDataForUnitList;


public class UnitController {
    public static List<Unit> unitsData;
    public Label distext1;
    public Label distext2;
    public Label distext3;
    public StackPane imageStackPane;
    public ImageView quizImage;
    @FXML
    private ImageView unitimage;
    @FXML
    private ImageView button1;

    @FXML
    private ImageView button2;

    @FXML
    private ImageView button3;

    public void handleButtonAction(ActionEvent actionEvent) {
    }
    @FXML
    public void setUnits(List<Unit> units) {
        if (units != null) {
            unitsData=units;
            System.out.println("傳進unit頁面"+units);
            distext1.setText(units.get(0).getDescContent1());
            button1.setUserData(units.get(0).getUnitId());
            distext2.setText(units.get(0).getDescContent2());
            button2.setUserData(units.get(0).getUnitId());
            distext3.setText(units.get(0).getDescContent3());
            button3.setUserData(units.get(0).getUnitId());
            UnitLabelData labelData1 = createUnitData(units.get(0).getUnitId(),units.get(0).getUnitName());
            quizImage.setUserData(labelData1);
            System.out.println("imageurl"+units.get(0).getPictureUrl1());
            Image image = new Image("file:///" + units.get(0).getPictureUrl1());
            unitimage.setImage(image);

        }
    }

    private UnitLabelData createUnitData(Long unitId, String unitName) {
        return new UnitLabelData(unitId,unitName);
    }

    @FXML
    public void handleQuizButtonAction(MouseEvent event) {
        ImageView clickedButton = (ImageView) event.getSource();
        UnitLabelData buttonData = (UnitLabelData) clickedButton.getUserData();
        Long unitId = (Long) buttonData.getUnitId();
        System.out.println("點擊開始測驗"+unitId);
        if (unitId != null) {
            showQuizDetails(unitId,event);
        } else {
            System.out.println("使用者可能未點擊");
        }
    }

    private void showQuizDetails(Long unitId, MouseEvent event) {
        String baseUrl = "http://localhost:8080/quiz";
        String serverUrl = baseUrl + "/" + unitId;
        try {

            String jsonResponse = HttpClientGetData.sendGetRequest(serverUrl);
            if (jsonResponse!=null) {
                System.out.println("jsonResponse進入parse前"+jsonResponse);
                List<Quiz> quizzes = (List<Quiz>) parseQuizzesJson(jsonResponse);
                System.out.println("quizzes parse後"+quizzes);
                FXMLLoader quizloader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
                Parent quizroot = quizloader.load();

                QuizController quizController = quizloader.getController();
                quizController.setQuizs(quizzes);
                Integer operation = 1;
                quizController.setCustomProperty(operation);

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
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<Quiz> parseQuizzesJson(String jsonResponse) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonResponse, new TypeReference<List<Quiz>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();}

    @FXML
    public void handleGoBackButtonAction(MouseEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unitlist.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
            UnitListController unitListController = loader.getController();
            unitListController.setUnits(unitsDataForUnitList);

            currentStage.setScene(scene);
            currentStage.setTitle("Unit List");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleVideoButtonAction(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());

        VideoController videoController = loader.getController();
        videoController.initMediaPlayer("file:///z:/SSTP/demo/videos/DEMO圖資畫面.mp4");
        System.out.println("videoScene"+scene);
        currentStage.setScene(scene);
        currentStage.setTitle("Video");
        currentStage.show();
    }


}
