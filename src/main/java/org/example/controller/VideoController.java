package org.example.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class VideoController {
    @FXML
    private MediaView mediaView;
    MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private boolean isReadyPlaying = false;
    private boolean isInitialized = false;
    private boolean wasReady = false;
    private static Stage newCurrentStage;

    public void initMediaPlayer(String url, Stage videoStage) {
        newCurrentStage=videoStage;
        if (!isInitialized) {
            Media media = new Media(url);
            mediaPlayer = new MediaPlayer(media);

            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.bufferProgressTimeProperty().addListener((observable, oldValue, newValue) -> {
                double bufferPercent = mediaPlayer.getBufferProgressTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds() * 100;
                System.out.println("緩衝中: " + bufferPercent + "%");
            });


            mediaPlayer.statusProperty().addListener(((observable, oldValue, newValue) -> {
                if (newValue == MediaPlayer.Status.READY) {
                    wasReady = true;
                    System.out.println("影片就緒");
                    isReadyPlaying = true;
//                    try {
//                        jumpToNextPage();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
                }else if(wasReady){
                    System.out.println("影片仍然就緒，只是使用者操作暫停播放");
                } else {
                    System.out.println("影片尚未");
                }
            }));
            System.out.println("有成功進入videocontroller");
            isInitialized = true;
        }
    }
    private void showWarningAlert() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("發生錯誤");
            alert.setContentText("尚未取得影片，請關閉後重新點擊播放影片開啟");

            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(newCurrentStage);

            alert.showAndWait();
        });
    }


    public void handlePauseButton(MouseEvent mouseEvent) {
        if (mediaPlayer != null && isReadyPlaying) {
            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.READY) {
                mediaPlayer.play();
                isPlaying = true;
            } else if (status == MediaPlayer.Status.PLAYING) {
                mediaPlayer.pause();
                isPlaying = false;
            } else if (status == MediaPlayer.Status.PAUSED) {
                mediaPlayer.play();
                isPlaying = true;
            }
        }
    }
    public boolean isMediaLoaded(String url) {
        Media media = new Media(url);
        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnReady(() -> {
            System.out.println("可以播放");
            isReadyPlaying = true;
        });

        mediaPlayer.setOnError(() -> {
            System.out.println("不能播放");
        });

        return true;
    }
//    @FXML
//    private void videoHandleBackButtonAction(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unit.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
//            UnitController unitController = loader.getController();
//            unitController.setUnits(unitsData);
//
//            System.out.println(currentStage+":unitstage");
//            currentStage.setScene(scene);
//            currentStage.setTitle("Unit");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
