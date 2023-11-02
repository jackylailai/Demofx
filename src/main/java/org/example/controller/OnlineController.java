package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.http.HttpClientGetData;
import org.example.vo.Unit;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.List;

import static org.example.netty.server.NettyClient.localhostip;


public class OnlineController {
    public Label timerLabel;

    public ImageView groupDone;
    public Label groupDoneLabel;
    public interface ResponseCallback {
        void onResponse(boolean is200);
    }
    @FXML
        private Stage stage;
//        @FXML
//        private Image jumpButton;

        // 在初始化控制器时设置stage字段
        public void initialize() {
//            onlineControl(new ResponseCallback() {
//                @Override
//                public void onResponse(boolean isStatusCode) {
//                    if (isStatusCode) {
//                        groupDone.setVisible(true);
//                        groupDoneLabel.setVisible(true);
//                    } else {
//                        System.out.println("尚未取得許可");
//                    }
//                }
//            });
            groupDone.setVisible(false);
            groupDoneLabel.setVisible(false);
        }
        public void setUnits(Stage currentStage) {
            Duration duration = Duration.seconds(1);

            KeyFrame keyFrame = new KeyFrame(duration, event -> {
                onlineControl(new ResponseCallback() {
                    @Override
                    public void onResponse(boolean isStatusCode) {
                        if (isStatusCode) {
                            groupDone.setVisible(true);
                            groupDoneLabel.setVisible(true);
                        } else {
                            System.out.println("尚未取得許可");
                        }
                    }
                });
                System.out.println("Timer finished. Perform window transition here.");
//                openNewOnlineUnitsWindow(currentStage);
//                groupDone.setVisible(true);
//                groupDoneLabel.setVisible(true);
            });
            Timeline timeline = new Timeline(keyFrame);
            timeline.setCycleCount(1);
            timeline.play();
        }
    @FXML
    private void openNewOnlineUnitsWindow(MouseEvent event) {
        FXMLLoader onlineUnitsLoader = new FXMLLoader(getClass().getResource("/unit2.fxml"));
        Parent onlineUnitsRoot = null;
        try {
            onlineUnitsRoot = onlineUnitsLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        OnlineUnitController onlineUnitsController = onlineUnitsLoader.getController();
        try{
            List<Unit> units = showUnitDetails(39L);
            System.out.println("unit list for online : "+units);
            onlineUnitsController.setUnits(units);
        }catch (NullPointerException e){
            throw new RuntimeException(e);
        }
//        OnlineUnitsController onlineUnitsController = onlineUnitsLoader.getController();
        Scene onlineUnitsScene = new Scene(onlineUnitsRoot);
        onlineUnitsScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Screen secondScreen = Screen.getScreens().stream()
                .filter(screen -> !screen.equals(Screen.getPrimary()))
                .findFirst()
                .orElse(Screen.getPrimary());

        currentStage.setWidth(1080);
        currentStage.setHeight(960);

        Rectangle2D secondScreenBounds = secondScreen.getVisualBounds();
        double centerX = secondScreenBounds.getMinX() + (secondScreenBounds.getWidth() - 1080) / 2;
        double centerY = secondScreenBounds.getMinY() + (secondScreenBounds.getHeight() - 960) / 2;
        currentStage.setX(centerX);
        currentStage.setY(centerY);
        currentStage.setAlwaysOnTop(true);
        currentStage.setScene(onlineUnitsScene);
        currentStage.setTitle("Online Units");
        System.out.println("Timer finished. Perform window transition here.");
    }
    public void onlineControl(ResponseCallback callback) {
            try {
                System.out.println("訊息開始寄出去");
                URL url = new URL("http://"+localhostip+":8080/user/online-controller");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == 200) {
                    System.out.println("Received a 200 OK response.");
                    callback.onResponse(true);
                } else {
                    System.out.println("Received a non-200 response: " + responseCode);
                    callback.onResponse(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
                callback.onResponse(false);
            }
    }
    private List<Unit> showUnitDetails(Long courseId) {
        String baseUrl = "http://"+localhostip+":8080/unit";
        String serverUrl = baseUrl + "/" + courseId;

        String jsonResponse = HttpClientGetData.sendGetRequest(serverUrl);
        if (jsonResponse!=null) {
            System.out.println("jsonResponse進入parse前"+jsonResponse);
            List<Unit> units = parseUnitsJson(jsonResponse);
            System.out.println("units parse後"+units);
            return units;
        }
        return null;
    }
    private java.util.List<Unit> parseUnitsJson(String jsonResponse) {
        List<Unit> units = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode unitJson = objectMapper.readTree(jsonResponse);
            //少掉列表迴圈
            Unit unit = new Unit();
            unit.setCourseId(unitJson.get("courseId").asLong());
            unit.setUnitId(unitJson.get("unitId").asLong());
            System.out.println("解析unitjson"+unitJson.get("unitId").asLong());
            unit.setUnitSubject(unitJson.get("unitSubject").asText());
            unit.setDescContent1(unitJson.get("descContent1").asText());
            unit.setDescContent2(unitJson.get("descContent2").asText());
            unit.setDescContent3(unitJson.get("descContent3").asText());
            unit.setPictureUrl1(unitJson.get("pictureUrl1").asText());
            unit.setPictureUrl2(unitJson.get("pictureUrl2").asText());
            units.add(unit);
            System.out.println("units" + unit);


        } catch(Exception e){
            e.printStackTrace();
        }
        return units;
    }
}
