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
import javafx.scene.control.*;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.example.Main.customFontForAll;
import static org.example.controller.UnitListController.unitsDataForUnitList;
import static org.example.netty.server.NettyClient.localhostip;


public class UnitController {
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
    public static int onlineControlCounts;

    @FXML
    private ImageView button2;

    @FXML
    private ImageView button3;
    public static String informationDetail;
    private static Stage videoStage;
    private static Stage storeUnitStage;
    MediaPlayer mediaPlayer;

    public void initialize() {
        addMouseHoverHandler(button1, distext1, title1);
        addMouseHoverHandler(button2, distext2, title2);
        addMouseHoverHandler(button3, distext3, title3);
        informationDetail = "單機";
    }

    private void addMouseHoverHandler(ImageView imageView, TextArea textArea, Label title) {
        imageView.setOnMousePressed(event -> {
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
//            label.setScaleY(newScaleY);
//            title.setScaleY(newScaleY);

        });

        imageView.setOnMouseReleased(event -> {
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

        });
    }

    @FXML
    public void setUnits(List<Unit> units) {
        if (units != null) {
            unitsData = units;
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

    private UnitLabelData createUnitData(Long unitId, String unitName) {
        return new UnitLabelData(unitId, unitName);
    }

    @FXML
    public void handleQuizButtonAction(MouseEvent event) {
        ImageView clickedButton = (ImageView) event.getSource();
        UnitLabelData buttonData = (UnitLabelData) clickedButton.getUserData();
        Long unitId = (Long) buttonData.getUnitId();
        System.out.println("點擊開始測驗" + unitId);
        if (unitId != null) {
            showQuizDetails(unitId, event);
        } else {
            System.out.println("使用者可能未點擊");
        }
    }

    private void showQuizDetails(Long unitId, MouseEvent event) {
//        String baseUrl = "http://"+localhostip+":8080/quiz";
        String baseUrl = "http://" + localhostip + ":8080/quiz/getQuizListByUnitId";
        String serverUrl = baseUrl + "/" + unitId;

        try {

            String jsonResponse = HttpClientGetData.sendGetRequest(serverUrl);
            if (jsonResponse != null) {
                System.out.println("jsonResponse進入parse前" + jsonResponse);
                List<Quiz> quizzes = (List<Quiz>) parseQuizzesJson(jsonResponse);
                System.out.println("quizzes parse後" + quizzes);
                FXMLLoader quizloader = new FXMLLoader(getClass().getResource("/quiz.fxml"));
                Parent quizroot = quizloader.load();

                QuizController quizController = quizloader.getController();
                quizController.setQuizs(quizzes);
                Integer operation = 1;
                quizController.setCustomProperty(operation);
                onlineControlCounts = 0;//開始定義不是線上考試
                Scene quizScene = new Scene(quizroot);
                quizScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                storeUnitStage=currentStage;
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
            } else {
                System.out.println("Quiz json 有問題");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Quiz> parseQuizzesJson(String jsonResponse) {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonResponse, new TypeReference<List<Quiz>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
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
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
////        Parent root = loader.load();
////        Scene scene = new Scene(root);
////        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
////        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
////
////        VideoController videoController = loader.getController();
////        videoController.initMediaPlayer("file:///z:/SSTP/demo/videos/DEMO圖資畫面.mp4");
////        System.out.println("videoScene"+scene);
////        currentStage.setScene(scene);
////        currentStage.setTitle("Video");
////        currentStage.show();
//
//
            System.out.println("點入影片按鈕");

//        Stage videoStage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
////        Parent root = loader.load();
////        Scene scene = new Scene(root);
////        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
////
////        VideoController videoController = loader.getController();
////        videoController.initMediaPlayer("file:///c:/SSTP/demo/videos/demo1單機版.mp4");
//        MediaPlayer.Status status = videoController.mediaPlayer.getStatus();
////        if(status==MediaPlayer.Status.READY){
////        }else{
////            System.out.println("有問題還沒載入影片");
////        }
////        videoStage.setAlwaysOnTop(true);
////        videoStage.setScene(scene);
////        videoStage.setTitle("Video");
////
////        videoStage.show();
////
////        videoStage.setOnCloseRequest(event -> {
////            if (videoController.mediaPlayer != null) {
////                videoController.mediaPlayer.stop();
////            }
////        });
//
//

//        VideoController videoController = new VideoController();
//
//        if (videoController.isMediaLoaded("file:///c:/SSTP/demo/videos/demo1單機版.mp4")) {
//            Stage videoStage = new Stage();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
//            videoController = loader.getController();
//            videoController.initMediaPlayer("file:///c:/SSTP/demo/videos/demo1單機版.mp4",videoStage);
//            videoStage.setAlwaysOnTop(true);
//            videoStage.setScene(scene);
//            videoStage.setTitle("Video");
//            videoStage.show();
//
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
            MediaPlayer beforeInitmediaplayer = beforeInitMediaPlayer("file:///c:/SSTP/demo/videos/demo1單機版.mp4");
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
//    public void handleVideoButtonAction(MouseEvent mouseEvent) {
//        showLoadingIndicator();
//
//        CompletableFuture.supplyAsync(() -> loadMedia("file:///c:/SSTP/demo/videos/demo1單機版.mp4"))
//                .thenAcceptAsync(mediaPlayer -> {
//
//                    hideLoadingIndicator();
//
//                    Platform.runLater(() -> {
//                        if (mediaPlayer != null) {
//                            loadFXMLAndCreateStage(mediaPlayer);
//                        } else {
//
//                            System.out.println("影片載入failure");
//                        }
//                    });
//                });
//    }
//    private void loadFXMLAndCreateStage(MediaPlayer mediaPlayer) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/video.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
//
//            VideoController videoController = loader.getController();
//            videoController.initMediaPlayer("file:///c:/SSTP/demo/videos/demo1單機版.mp4");
//
//            Stage videoStage = new Stage();
//            videoStage.setAlwaysOnTop(true);
//            videoStage.setScene(scene);
//            videoStage.setTitle("Video");
//            videoStage.show();
//
//            videoStage.setOnCloseRequest(event -> {
//                if (mediaPlayer != null) {
//                    mediaPlayer.stop();
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    public void showLoadingIndicator() {
//        loadingPane.setVisible(true);
//    }
//
//    public void hideLoadingIndicator() {
//        loadingPane.setVisible(false);
//    }
//    private MediaPlayer loadMedia(String url) {
//        Media media = new Media(url);
//        mediaPlayer = new MediaPlayer(media);
//
//        mediaPlayer.setOnReady(() -> {
//            System.out.println("影片就緒");
//        });
//
//        mediaPlayer.setOnError(() -> {
//            System.out.println("加載影片出錯");
//        });
//
//
//        return mediaPlayer;
//    }


