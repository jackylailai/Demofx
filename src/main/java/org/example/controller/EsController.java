package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.example.http.HttpClientGet;
import org.example.http.HttpClientGetData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class EsController {
    @FXML
    private TextArea textArea;
    public void initializeUserData(JsonNode jsonNode) {
        String name = jsonNode.get("name").asText();
        String info = "學員 :   "+name;
        textArea.setText(info);
    }

    public void handleLogoutButtonAction(ActionEvent actionEvent) {
        String serverUrl = "http://localhost:8080/user/logout";
        HttpURLConnection connection = HttpClientGet.sendGetRequest(serverUrl);
        if (isHttpResponseSuccessful(connection)) {
            showSuccessAlert("登出成功");
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent loginRoot = null;
            try {
                loginRoot = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Scene loginScene = new Scene(loginRoot);

            currentStage.setScene(loginScene);
            currentStage.setTitle("Login");
        } else {

        }
    }
    public boolean isHttpResponseSuccessful(HttpURLConnection connection) {
        try {
            int responseCode = connection.getResponseCode();
            return responseCode == 200;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("成功");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
//控制training的按鈕
    public void handleCourseTrainingClick(ActionEvent actionEvent) {

        String jsonResponse = HttpClientGetData.sendGetRequest("http://localhost:8080/course");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
            String courseName = jsonNode.get("courseName").asText();
            System.out.println("courseName"+courseName);
            FXMLLoader courseloader = new FXMLLoader(getClass().getResource("/courseitem.fxml"));
            Parent courseroot = courseloader.load();

            CourseItemController courseItemController = courseloader.getController();
            courseItemController.setCourseData(jsonNode);

            Scene courseItemScene = new Scene(courseroot);
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(courseItemScene);
            currentStage.setTitle("Course Item");
        } catch (JsonProcessingException e) {
            showFailureAlert("操作失敗，請重試。");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showFailureAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("失敗");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
    //方法1 建立entity 然後extend
//方法2 bean utils copy 不一樣的物件 dto
    //以免改版  定義dto 才能被共享
    //maven libary build
//                swagger build stage

// gson 也可以試看看
    //objectmapper      if not-->>json string to object



//    public void handleCourseTrainingClick(ActionEvent actionEvent) {
//        try {
//
//            HttpURLConnection connection = HttpClientGet.sendGetRequest("http://course");
//
//            if (isHttpResponseSuccessful(connection)) {
//
//                List<Course> courses = parseApiResponse(connection);
//
//                FXMLLoader courseloader = new FXMLLoader(getClass().getResource("/courseitem.fxml"));
//                Parent courseroot = courseloader.load();
//
//
//                CourseItemController courseItemController = courseloader.getController();
//                courseItemController.setCourses(courses);
//
//
//
//                Scene courseItemScene = new Scene(courseroot);
//                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                currentStage.setScene(courseItemScene);
//                currentStage.setTitle("Course Item");
//            } else {
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private List<Course> parseApiResponse(HttpURLConnection connection) throws IOException {
//        List<Course> courses = new ArrayList<>();
//
//        try (InputStream inputStream = connection.getInputStream()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            JsonNode root = objectMapper.readTree(inputStream);
//
//            if (root.isArray()) {
//                for (JsonNode courseNode : root) {
//                    Long id = courseNode.get("id").asLong();
//                    String name = courseNode.get("name").asText();
//                    String description = courseNode.get("description").asText();
//
//                    Course course = new Course(id, name, description);
//                    courses.add(course);
//                }
//            }
//        }
//
//        return courses;
//    }
}
