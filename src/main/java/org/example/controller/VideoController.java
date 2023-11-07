package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.IOException;
import java.util.Objects;

import static org.example.controller.UnitController.unitsData;

public class VideoController {
    @FXML
    private MediaView mediaView;
    MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private boolean isReadyPlaying = false;

    public void initMediaPlayer(String url) {
        Media media = new Media(url);
        System.out.println();
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.statusProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue== MediaPlayer.Status.READY){
                System.out.println("影片就緒");
//                mediaPlayer.play();
                isReadyPlaying=true;
            }
            else{
                System.out.println("影片尚未");
            }
        }));
        System.out.println("有成功進入videocontroller");

//        MediaPlayer.Status status = mediaPlayer.statusProperty().get();
//        if(status==MediaPlayer.Status.READY){
//            mediaPlayer.play();
//        }else{
//
//        }
    }

    public void handlePauseButton(MouseEvent mouseEvent) {
        if (mediaPlayer != null) {
//            if(isReadyPlaying) {
                if (mediaPlayer.getStatus() == MediaPlayer.Status.READY) {
                    if (isPlaying) {
                        mediaPlayer.pause();
                    } else {
                        mediaPlayer.play();
                    }
                    isPlaying = !isPlaying;
                }
//            }
        }
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
