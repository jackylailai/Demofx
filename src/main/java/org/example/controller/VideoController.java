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

    public void initMediaPlayer(MediaPlayer mediaPlayerFromUnit) {
//        newCurrentStage=videoStage;
        mediaPlayer=mediaPlayerFromUnit;
        if (!isInitialized) {
//            Media media = new Media(url);
//            mediaPlayer = new MediaPlayer(media);

            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.bufferProgressTimeProperty().addListener((observable, oldValue, newValue) -> {
                double bufferPercent = mediaPlayer.getBufferProgressTime().toSeconds() / mediaPlayer.getTotalDuration().toSeconds() * 100;
                System.out.println("double check緩衝中: " + bufferPercent + "%");
            });


            mediaPlayer.statusProperty().addListener(((observable, oldValue, newValue) -> {
                if (newValue == MediaPlayer.Status.READY) {
                    wasReady = true;
                    System.out.println("double check影片就緒");
                    isReadyPlaying = true;
//                    try {
//                        jumpToNextPage();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
                }else if(wasReady){
                    System.out.println("double check影片仍然就緒，只是使用者操作暫停播放");
                } else {
                    System.out.println("double check影片尚未");
                }
            }));
            System.out.println("有成功進入videocontroller");
            isInitialized = true;
        }
    }


    public void handlePauseButton(MouseEvent mouseEvent) {
        if (mediaPlayer!= null && isReadyPlaying) {
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

}
