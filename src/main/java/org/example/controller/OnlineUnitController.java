package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.http.HttpClientGetData;
import org.example.modeldata.UnitLabelData;
import org.example.vo.Quiz;
import org.example.vo.Unit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.example.Main.customFontForAll;
import static org.example.controller.QuizController.testTime;
import static org.example.controller.UnitController.*;
import static org.example.controller.UnitListController.unitsDataForUnitList;
import static org.example.netty.handler.ClientHandler.ctxFromHandler;
import static org.example.netty.handler.ClientHandler.sendMessageToServer;
import static org.example.netty.server.NettyClient.localhostip;

public class OnlineUnitController {
    public TextArea distext1;
    public TextArea distext2;
    public TextArea distext3;
    public StackPane imageStackPane;
    public ImageView quizImage;
    public Label title3;
    public Label title2;
    public Label title1;
    @FXML
    public static ImageView blockImageForStatic;
    public ImageView blockImage;
    public static Long unitIdFromOnlineUnit;
    public static MouseEvent eventFromOnlineUnit;
    @FXML
    private ImageView unitimage;
    @FXML
    private ImageView button1;

    @FXML
    private ImageView button2;

    @FXML
    private ImageView button3;
    private static Stage videoStage;
    private static Stage storeUnitStage;
    private AtomicBoolean isZoomed1 = new AtomicBoolean(false);
    private AtomicBoolean isZoomed2 = new AtomicBoolean(false);
    private AtomicBoolean isZoomed3 = new AtomicBoolean(false);
    MediaPlayer mediaPlayer;
    public void initialize() {
        addMouseHoverHandler(button1, distext1, title1, isZoomed1);
        addMouseHoverHandler(button2, distext2, title2, isZoomed2);
        addMouseHoverHandler(button3, distext3, title3, isZoomed3);
//        setUnits(units);
        blockImageForStatic=blockImage;
        blockImage.setVisible(false);
        informationDetail="號手";
    }
    @FXML
    public void setUnits(List<Unit> units) {
        if (units != null) {
            unitsData =units;
            System.out.println("傳進unit頁面" + units);
            distext1.setText(units.get(0).getDescContent1());
            distext1.setFont(Font.font(customFontForAll.getFamily(), 18));
            distext1.setWrapText(true);
            System.out.println("distext font" + distext1.getFont().getSize() + distext1.getFont().getName());
            button1.setUserData(units.get(0).getUnitId());
            distext2.setText(units.get(0).getDescContent2());
            distext2.setFont(Font.font(customFontForAll.getFamily(), 18));
            distext2.setWrapText(true);
            button2.setUserData(units.get(0).getUnitId());
            System.out.println("distext2 font" + distext2.getFont().getSize() + distext2.getFont().getName());
            distext3.setText(units.get(0).getDescContent3());
            distext3.setWrapText(true);
            distext3.setFont(Font.font(customFontForAll.getFamily(), 18));
            button3.setUserData(units.get(0).getUnitId());
            System.out.println("distext3 font" + distext3.getFont().getSize() + distext3.getFont().getName());
            UnitLabelData labelData1 = createUnitData(units.get(0).getUnitId(), units.get(0).getUnitName());
            quizImage.setUserData(labelData1);
            System.out.println("imageurl" + units.get(0).getPictureUrl1());
            Image image = new Image("file:///" + units.get(0).getPictureUrl1());
            unitimage.setImage(image);
        }
    }
    private void addMouseHoverHandler(ImageView imageView, TextArea textArea, Label title, AtomicBoolean isZoomed) {
//        imageView.setOnMousePressed(event -> {
//            double currentScaleX = imageView.getScaleX();
//            double currentScaleY = imageView.getScaleY();
//            double translateY = imageView.getBoundsInParent().getHeight() * (1.0-3.0) / 2.0;
//            textArea.setFont(new Font(customFontForAll.getFamily(), textArea.getFont().getSize() * 1.2));
//            System.out.println(textArea.getFont().getName()+textArea.getFont().getSize()+",data");
//            title.setFont(new Font(title.getFont().getName(), title.getFont().getSize() * 1.2));
////            imageView.setScaleY(newScaleY);
//            imageView.setTranslateY(translateY);
//            textArea.setTranslateY(translateY*1.8);
//            title.setTranslateY(translateY*1.8);
//            imageView.setScaleX(currentScaleX * 1);
//            imageView.setScaleY(currentScaleY * 3);
//            textArea.setPrefHeight(textArea.getPrefHeight() * 3);
////            label.setScaleY(newScaleY);
////            title.setScaleY(newScaleY);
//
//        });
//
//        imageView.setOnMouseReleased(event -> {
//            textArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getSize() / 1.2));
//            title.setFont(new Font(title.getFont().getName(), title.getFont().getSize() / 1.2));
//            System.out.println(textArea.getFont().getName()+textArea.getFont().getSize()+",回來變小之後data");
//            imageView.setScaleX(1.0); // 恢复到原始大小
//            imageView.setScaleY(1.0);
//            imageView.setTranslateY(0.0); // 重置垂直方向上的位移
//            textArea.setTranslateY(0.0);
//            title.setTranslateY(0.0);
//            textArea.setScaleY(1.0);
//            title.setScaleY(1.0);
//            textArea.setPrefHeight(textArea.getPrefHeight() / 3);
//
//        });
        imageView.setOnMousePressed(event -> {
            if (isZoomed.get()) {
                textArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getSize() / 1.2));
                title.setFont(new Font(title.getFont().getName(), title.getFont().getSize() / 1.2));
                System.out.println(textArea.getFont().getName() + textArea.getFont().getSize() + ",回來變小之後data");
                imageView.setScaleX(1.0);
                imageView.setScaleY(1.0);
                imageView.setTranslateY(0.0);
                textArea.setTranslateY(0.0);
                title.setTranslateY(0.0);
                textArea.setScaleY(1.0);
                title.setScaleY(1.0);
                textArea.setPrefHeight(textArea.getPrefHeight() / 3);
            } else {
                double currentScaleX = imageView.getScaleX();
                double currentScaleY = imageView.getScaleY();
                double translateY = imageView.getBoundsInParent().getHeight() * (1.0 - 3.0) / 2.0;
                textArea.setFont(new Font(customFontForAll.getFamily(), textArea.getFont().getSize() * 1.2));
                System.out.println(textArea.getFont().getName() + textArea.getFont().getSize() + ",data");
                title.setFont(new Font(title.getFont().getName(), title.getFont().getSize() * 1.2));
//            imageView.setScaleY(newScaleY);
                imageView.setTranslateY(translateY);
                textArea.setTranslateY(translateY * 1.8);
                title.setTranslateY(translateY * 1.8);
                imageView.setScaleX(currentScaleX * 1);
                imageView.setScaleY(currentScaleY * 3);
                textArea.setPrefHeight(textArea.getPrefHeight() * 3);
            }

            isZoomed.set(!isZoomed.get());
        });
    }



    private UnitLabelData createUnitData(Long unitId, String unitName) {
        return new UnitLabelData(unitId,unitName);
    }


    @FXML
    public void handleQuizButtonAction(MouseEvent event) {
        ImageView clickedButton = (ImageView) event.getSource();
        UnitLabelData buttonData = (UnitLabelData) clickedButton.getUserData();
        Long unitId = (Long) buttonData.getUnitId();
        eventFromOnlineUnit=event;
        unitIdFromOnlineUnit=unitId;
        //傳入test1 get
        sendMessageToServer("get",ctxFromHandler);
        System.out.println("點擊開始測驗UnitId"+unitId);
//        if (unitId != null) {
//            showQuizDetails(unitId,event);
//        } else {
//            System.out.println("使用者可能未點擊");
//        }
    }

    public static void showQuizDetails(Long unitId, MouseEvent event,int onlineControl) {
//        String baseUrl = "http://"+localhostip+":8080/quiz";
        String baseUrl = "http://"+localhostip+":8080/quiz/getQuizListByUnitId";
        String serverUrl = baseUrl + "/" + unitId;
        try {

            String jsonResponse = HttpClientGetData.sendGetRequest(serverUrl);
            if (jsonResponse != null) {
                System.out.println("jsonResponse進入parse前" + jsonResponse);
                List<Quiz> quizzes = (List<Quiz>) parseQuizzesJson(jsonResponse);
                System.out.println("quizzes parse後" + quizzes);
                FXMLLoader quizloader = new FXMLLoader(OnlineUnitController.class.getResource("/quiz.fxml"));
                Parent quizroot = quizloader.load();

                QuizController quizController = quizloader.getController();
                quizController.setQuizs(quizzes);
                if(onlineControl==2){
                    Integer operation = 3;
                    quizController.setCustomProperty(operation);
                    System.out.println(onlineControlCounts+":::::onlinecotrolcounts這邊應該要是2");
                    onlineControlCounts=2;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date currentDate = new Date();
                    testTime= dateFormat.format(currentDate);
                    System.out.println(testTime+":::::test2Time");
                }else{
                    //剛開始test1進入
                    onlineControlCounts=1;
                    System.out.println(onlineControlCounts+"::operationcontrol=1:::onlinecotrolcounts這邊應該要是1");
                    Integer operation = 1;
                    quizController.setCustomProperty(operation);
                }



                try {
                    Scene quizScene = new Scene(quizroot);
                    quizScene.getStylesheets().add(Objects.requireNonNull(OnlineUnitController.class.getResource("/globalStyles.css")).toExternalForm());
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
                    System.out.println("quizScene" + quizScene);
                    currentStage.setAlwaysOnTop(true);
                    currentStage.setScene(quizScene);
                    currentStage.setTitle("Quiz List");
                }catch (NullPointerException e) {
                    // 處理NullPointerException，例如記錄錯誤或執行其他操作
                    System.out.println("null point error");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<Quiz> parseQuizzesJson(String jsonResponse) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonResponse, new TypeReference<List<Quiz>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();}
    public static void blockImageFunc(){
        if(blockImageForStatic==null){
            System.out.println("block is null");
        }else {
            blockImageForStatic.setVisible(true);
        }
    }
    public static void displayImageFunc(){
        if(blockImageForStatic==null){
            System.out.println("block is null");
        }else {
            blockImageForStatic.setVisible(false);
        }
    }
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
//        Stage videoStage = new Stage();
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
//
//        VideoController videoController = loader.getController();
//        videoController.initMediaPlayer("file:///c:/SSTP/demo/videos/demo1多人版.mp4");
//
//        System.out.println(videoController);
//        videoStage.setAlwaysOnTop(true);
//        videoStage.setScene(scene);
//        videoStage.setTitle("Video");
//        videoStage.show();
//
//        videoStage.setOnCloseRequest(event -> {
//            if (videoController.mediaPlayer != null) {
//                videoController.mediaPlayer.stop();
//            }
////        });
//        VideoController videoController = new VideoController();
//
//        if (videoController.isMediaLoaded("file:///c:/SSTP/demo/videos/demo1多人版.mp4")) {
//            Stage videoStage = new Stage();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
//            videoController = loader.getController();
//            videoController.initMediaPlayer("file:///c:/SSTP/demo/videos/demo1多人版.mp4", videoStage);
//            videoStage.setAlwaysOnTop(true);
//            videoStage.setScene(scene);
//            videoStage.setTitle("Video");
//            videoStage.show();
//
//            VideoController finalVideoController = videoController;
//            videoStage.setOnCloseRequest(event -> {
//                if (finalVideoController.mediaPlayer != null) {
//                    finalVideoController.mediaPlayer.stop();
//                }
//            });
//        } else {
//
//            System.out.println("video載入失敗");
//
//        }
        videoStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
        VideoController videoController = loader.getController();
        MediaPlayer beforeInitmediaplayer = beforeInitMediaPlayer("file:///c:/SSTP/demo/videos/demo1多人版.mp4");
        videoController.initMediaPlayer(beforeInitmediaplayer);
        videoStage.setAlwaysOnTop(true);
        videoStage.setScene(scene);
        videoStage.setTitle("Video");
        //            videoStage.show();

        videoStage.setOnCloseRequest(event -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        });
    }
    private MediaPlayer beforeInitMediaPlayer(String url) {
        Media media = new Media(url);
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.bufferProgressTimeProperty().addListener((observable, oldValue, newValue) -> {
            double bufferPercent = mediaPlayer.getBufferProgressTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds() * 100;
            System.out.println("緩衝中: " + bufferPercent + "%");
        });
        mediaPlayer.setOnReady(() -> {
            System.out.println("影片就绪");
            videoStage.show();
        });
        mediaPlayer.setOnError(()->{
            System.out.println("錯誤");
            showWarningAlert();
        });
        System.out.println("有成功跑完before init video");
        return mediaPlayer;
    }
    private void showWarningAlert() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("發生錯誤");
            alert.setContentText("載入失敗，請重新點擊播放影片開啟");

            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(storeUnitStage);

            alert.showAndWait();
        });
    }
}
