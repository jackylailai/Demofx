package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.http.HttpClientPostLogin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.modelDTO.NettyDTO;
import org.example.modelDTO.UserDTO;
import org.example.netty.controller.NettyClientMsgController;
import org.example.netty.handler.ClientHandler;

import java.io.IOException;

import static org.example.utils.SHAService.getSHA256StrJava;

public class LoginController {
    @FXML
    public ImageView loginButtonView;
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;
//sub controller 處理list可能會 根據不同輸入來源 要
    @FXML
    private Button loginButton;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage; //
    }

    @FXML
    private void initialize() {
        loginButtonView.setOnMouseClicked(event -> {
            // 調用登錄按鈕的處理程序
            try {
                handleLoginButtonAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void handleLoginButtonAction() throws IOException {
        // 當登入按鈕被點擊時執行的程式碼
        String username = usernameField.getText();
        String password = passwordField.getText();

        password = getSHA256StrJava(password);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setCtxId(NettyClientMsgController.getClientCtxId());
        // 創建JSON數據，實際上需要根據你的需求創建正確的JSON數據
//        String jsonResponse = HttpClientPostLogin.sendLoginRequest(username, password);
        String jsonResponse = HttpClientPostLogin.sendLoginRequestDTO(userDTO);
        System.out.println(jsonResponse + "jsonResponse");
        if (jsonResponse != null && !jsonResponse.isEmpty() && jsonResponse.startsWith("{")) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                NettyDTO nettyDTO = objectMapper.readValue(jsonResponse, NettyDTO.class);
                System.out.println("nettyDTO : " + nettyDTO);
                int level = jsonNode.get("level").asInt();
                if (level == 1) {
                    System.out.println("1你是學生!");
                    FXMLLoader tsLoader = new FXMLLoader(getClass().getResource("/ts.fxml"));
                    Parent tsRoot = tsLoader.load();
                    Scene tsScene = new Scene(tsRoot);
                    tsScene.getStylesheets().add(getClass().getResource("/globalStyles.css").toExternalForm());
                    TsController tsController = tsLoader.getController();
                    tsController.initializeUserData(jsonNode);
                    System.out.println("這裡是登入確認是學生的訊息 primary stage" + primaryStage);
                    primaryStage.setAlwaysOnTop(true);
                    primaryStage.setScene(tsScene);
                    primaryStage.setTitle("TS");
                } else if (level == 2) {
                    System.out.println("2你是教官");
                    FXMLLoader msLoader = new FXMLLoader(getClass().getResource("/ms.fxml"));
                    Parent msRoot = msLoader.load();
                    Scene msScene = new Scene(msRoot);

                    MsController msController = msLoader.getController();
                    primaryStage.setScene(msScene);
                    primaryStage.setTitle("MS");
                } else {
                    System.out.println("都不是1跟2");
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else if (jsonResponse.equals("401")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("登入失敗");
            alert.setHeaderText("密碼錯誤");
            alert.setContentText("請檢察您的密碼。");
            alert.showAndWait();
        } else if(jsonResponse.equals("500")){
            System.out.println("Invalid JSON response: 帳號密碼都錯誤" + jsonResponse);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("登入失敗");
            alert.setHeaderText("帳號或密碼錯誤");
            alert.setContentText("請檢察您的帳號或密碼。");
            alert.showAndWait();
        }
    }
}