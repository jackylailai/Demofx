package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.http.HttpClientPostLogin;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;
    private Stage primaryStage; // 添加 Stage 成员变量

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage; //
    }

    @FXML
    private void initialize() {
        // 初始化程式碼，如果需要的話
    }

    @FXML
    private void handleLoginButtonAction() throws IOException {
        // 當登入按鈕被點擊時執行的程式碼
        String username = usernameField.getText();
        String password = passwordField.getText();
        // 創建JSON數據，這只是一個示例，實際上需要根據你的需求創建正確的JSON數據
        String jsonResponse = HttpClientPostLogin.sendLoginRequest(username, password);
        System.out.println(jsonResponse+"jsonResponse");
        if (jsonResponse != null && !jsonResponse.isEmpty() && jsonResponse.startsWith("{")) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                int level = jsonNode.get("level").asInt();
                if (level == 1) {
                    System.out.println("1你是學生!");
                    FXMLLoader esLoader = new FXMLLoader(getClass().getResource("/es.fxml"));
                    Parent esRoot = esLoader.load();
                    Scene esScene = new Scene(esRoot);
                    EsController esController = esLoader.getController();
                    esController.initializeUserData(jsonNode);

                    primaryStage.setScene(esScene);
                    primaryStage.setTitle("ES");
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
        } else {

            System.out.println("Invalid JSON response: " + jsonResponse);
        }

    }
}