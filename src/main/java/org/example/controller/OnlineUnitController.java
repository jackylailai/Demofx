package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.http.HttpClientGetData;
import org.example.modaldata.UnitLabelData;
import org.example.vo.Quiz;
import org.example.vo.Unit;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.example.Main.customFontForAll;
import static org.example.controller.UnitListController.unitsDataForUnitList;

public class OnlineUnitController {
    public static List<Unit> unitsData;
    public TextArea distext1;
    public TextArea distext2;
    public TextArea distext3;
    public StackPane imageStackPane;
    public ImageView quizImage;
    public Label title3;
    public Label title2;
    public Label title1;
    @FXML
    private ImageView unitimage;
    @FXML
    private ImageView button1;

    @FXML
    private ImageView button2;

    @FXML
    private ImageView button3;

    public void initialize() {
        addMouseHoverHandler(button1,distext1,title1);
        addMouseHoverHandler(button2,distext2,title2);
        addMouseHoverHandler(button3,distext3,title3);
        setUnits();
    }

    private void addMouseHoverHandler(ImageView imageView,TextArea textArea,Label title) {
        imageView.setOnMousePressed(event -> {
            double currentScaleX = imageView.getScaleX();
            double currentScaleY = imageView.getScaleY();
            double translateY = imageView.getBoundsInParent().getHeight() * (1.0-3.0) / 2.0;
            textArea.setFont(new Font(customFontForAll.getFamily(), textArea.getFont().getSize() * 1.2));
            System.out.println(textArea.getFont().getName()+textArea.getFont().getSize()+",data");
            title.setFont(new Font(title.getFont().getName(), title.getFont().getSize() * 1.2));
//            imageView.setScaleY(newScaleY);
            imageView.setTranslateY(translateY);
            textArea.setTranslateY(translateY*1.8);
            title.setTranslateY(translateY*1.8);
            imageView.setScaleX(currentScaleX * 1);
            imageView.setScaleY(currentScaleY * 3);
            textArea.setPrefHeight(textArea.getPrefHeight() * 3);
//            label.setScaleY(newScaleY);
//            title.setScaleY(newScaleY);

        });

        imageView.setOnMouseReleased(event -> {
            textArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getSize() / 1.2));
            title.setFont(new Font(title.getFont().getName(), title.getFont().getSize() / 1.2));
            System.out.println(textArea.getFont().getName()+textArea.getFont().getSize()+",回來變小之後data");
            imageView.setScaleX(1.0); // 恢复到原始大小
            imageView.setScaleY(1.0);
            imageView.setTranslateY(0.0); // 重置垂直方向上的位移
            textArea.setTranslateY(0.0);
            title.setTranslateY(0.0);
            textArea.setScaleY(1.0);
            title.setScaleY(1.0);
            textArea.setPrefHeight(textArea.getPrefHeight() / 3);

        });
    }
    @FXML
    public void setUnits() {

//            unitsData=units;
//            System.out.println("傳進unit頁面"+units);
            String str1= "這支影片用來介紹偵蒐測項軟體-信號偵蒐測向課程 - 信號搜索單元。\n" +
                    "信號搜索是偵蒐車進到新環境的第一個動作，快速且大範圍蒐集周遭環境的定頻訊號，了解周遭的訊號環境，才能進一步判斷訊息重要性的優先順序，進行追蹤、信號監聽、跳頻信號源判斷。藉此掌握敵人之通訊或傳輸資料，並藉此獲取情報資料，掌握敵人的動向，作為戰鬥指導的依據。\n";
            distext1.setText(str1);
            distext1.setFont(Font.font(customFontForAll.getFamily(), 18));
            distext1.setWrapText(true);
            System.out.println("distext font"+distext1.getFont().getSize()+distext1.getFont().getName());
//            button1.setUserData(units.get(0).getUnitId());
            String str2="現代化戰爭中，天空充斥著無數的電子訊號，例如：廣播訊號，電視訊號以及各種無線電訊號，因此在進行信號搜索時，為了避免獲得的資料過於龐雜，需要針對搜索目標，設定限縮條件 ，並依照條件使用剔除功能， 讓搜索結果更加貼近目標，以便進行後續追踨、監視或干擾…等行動。";
            distext2.setText(str2);
            distext2.setFont(Font.font(customFontForAll.getFamily(), 18));
            distext2.setWrapText(true);
//            button2.setUserData(units.get(0).getUnitId());
            System.out.println("distext2 font"+distext2.getFont().getSize()+distext2.getFont().getName());
            String str3="本次模擬情境中，指揮部接到情報資訊，發現基隆外海疑似有敵方無人機出沒，派遣一台偵蒐車到場，搜索出無人機信號，以驗證情報真偽。\n" +
                    "\n" +
                    "1.\t使用搜索功能，找到400~500mhz頻段，「頻率間隔」12.5，「截收信號強度」-70db以上可疑定頻訊號\n" +
                    "2.\t剔除商用無人機433 Mhz波道訊號\n" +
                    "3.\t剔除480～490mhz波段訊號 \n" +
                    "4.\t剔除100～270度來向角訊號";
            distext3.setText(str3);
            distext3.setWrapText(true);
            distext3.setFont(Font.font(customFontForAll.getFamily(), 18));
//            button3.setUserData(units.get(0).getUnitId());
            System.out.println("distext3 font"+distext3.getFont().getSize()+distext3.getFont().getName());
//            UnitLabelData labelData1 = createUnitData(units.get(0).getUnitId(),units.get(0).getUnitName());
//            quizImage.setUserData(labelData1);
//            System.out.println("imageurl"+units.get(0).getPictureUrl1());
            Image image = new Image("file:///" + "Z:\\\\SSTP\\\\demo\\\\images\\\\dfcs.jpg");
            unitimage.setImage(image);


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

                Rectangle2D secondScreenBounds = secondScreen.getBounds();

                double windowWidth = 335;
                double windowHeight = 540;

                currentStage.setWidth(335);
                currentStage.setHeight(540);

                double newX = secondScreenBounds.getMaxX() - windowWidth;
                double newY = secondScreenBounds.getMaxY() - windowHeight;

                currentStage.setX(newX);
                currentStage.setY(newY);
                System.out.println("quizScene"+quizScene);
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
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
//
//        VideoController videoController = loader.getController();
//        videoController.initMediaPlayer("file:///z:/SSTP/demo/videos/DEMO圖資畫面.mp4");
//        System.out.println("videoScene"+scene);
//        currentStage.setScene(scene);
//        currentStage.setTitle("Video");
//        currentStage.show();
        Stage videoStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());

        VideoController videoController = loader.getController();
        videoController.initMediaPlayer("file:///z:/SSTP/demo/videos/DEMO圖資畫面.mp4");

        videoStage.setScene(scene);
        videoStage.setTitle("Video");
        videoStage.show();
    }
}
