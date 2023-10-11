package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.http.HttpClientGet;
import org.example.http.HttpClientGetData;
import org.example.vo.Course;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.Main.*;

public class TsController {
    @FXML
    public ImageView logoutButtonView;
    public ImageView courseTraining;
    public Text text1;
    public Text text2;
    public Text text3;
    @FXML
    private Label textArea;
    public static JsonNode jsonNodeForUser;
    public void initializeUserData(JsonNode jsonNode) {
        jsonNodeForUser = jsonNode;
        String name = jsonNode.get("name").asText();
        String info = "學員 :   "+name;
//        User user = new User();
        textArea.setText(info);
        textArea.setFont(lightFontForAll);
//        textArea.setEditable(false);
    }
    public void initialize() {
        logoutButtonView.setOnMouseClicked(event -> {
            // 調用登出按鈕的處理程序
            ActionEvent actionEvent = new ActionEvent(event.getSource(), event.getTarget());
            handleLogoutButtonAction(actionEvent);
        });
        courseTraining.setOnMouseClicked(event -> {
            ActionEvent actionEvent = new ActionEvent(event.getSource(), event.getTarget());
            handleCourseTrainingClick(actionEvent);
        });
        text1.setFont(customFontForAll);
        text2.setFont(customFontForAll);
        text3.setFont(customFontForAll);

    }
    public void handleLogoutButtonAction(ActionEvent actionEvent) {
        String serverUrl = "http://localhost:8080/user/logout";
        HttpURLConnection connection = HttpClientGet.sendGetRequest(serverUrl);
        if (isHttpResponseSuccessful(connection)) {
            showSuccessAlert("登出成功");
            Platform.runLater(() -> {

                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
                Parent loginRoot = null;
                try {
                    loginRoot = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LoginController loginController = loader.getController();
                loginController.setPrimaryStage(currentStage);
                Scene loginScene = new Scene(loginRoot);
                System.out.println("es stage"+currentStage);
                currentStage.setScene(loginScene);
                currentStage.setTitle("Login");
            });
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
//    public void handleCourseTrainingClick(ActionEvent actionEvent) {
//
//        String jsonResponse = HttpClientGetData.sendGetRequest("http://localhost:8080/course");
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            JsonNode jsonNode = objectMapper.readTree(jsonResponse);
//            String courseName = jsonNode.get("courseName").asText();
//            System.out.println("courseName"+courseName);
//            FXMLLoader courseloader = new FXMLLoader(getClass().getResource("/courseitem.fxml"));
//            Parent courseroot = courseloader.load();
//
//            CourseItemController courseItemController = courseloader.getController();
//            courseItemController.setCourseData(jsonNode);
//
//            Scene courseItemScene = new Scene(courseroot);
//            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//            currentStage.setScene(courseItemScene);
//            currentStage.setTitle("Course Item");
//        } catch (JsonProcessingException e) {
//            showFailureAlert("操作失敗，請重試。");
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

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



    public void handleCourseTrainingClick(ActionEvent actionEvent) {
        try {

            String jsonResponse = HttpClientGetData.sendGetRequest("http://localhost:8080/course");
            if (jsonResponse!=null) {

                List<Course> courses = parseCoursesJson(jsonResponse);

                FXMLLoader courseloader = new FXMLLoader(getClass().getResource("/courseitem.fxml"));
                Parent courseroot = courseloader.load();


                CourseItemController courseItemController = courseloader.getController();
                courseItemController.setCourses(courses);



                Scene courseItemScene = new Scene(courseroot);
                courseItemScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                currentStage.setScene(courseItemScene);
                currentStage.setTitle("Course Item");
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Course> parseCoursesJson(String jsonResponse) {
        List<Course> courses = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> courseNameList = new ArrayList<>();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            if (jsonNode.isArray()) {
                for (JsonNode courseJson : jsonNode) {
                    Course course = new Course();
//                    course.setId(courseJson.get("id").asLong());
                    course.setCourseId(courseJson.get("courseId").asLong());
                    course.setCourseType(courseJson.get("courseType").asInt());
                    course.setCourseName(courseJson.get("courseName").asText());
                    course.setCourseSchedule(courseJson.get("courseSchedule").asText());
                    course.setCourseDesc(courseJson.get("courseDesc").asText());
                    course.setCreditUnits(courseJson.get("creditUnits").asInt());
//                    course.setState(courseJson.get("state").asInt());
//                    course.setLongDate(courseJson.get("longDate").asLong());
//                    course.setCreateDate(courseJson.get("createDate").asText());
//                    course.setUpdateDate(courseJson.get("updateDate").asText());
                    courses.add(course);
                    System.out.println("course"+courses);
                }
                System.out.println("courselist"+courses);
                for (Course course : courses) {
                    String courseName = course.getCourseName();
                    System.out.println("Course Name: " + courseName);
                    courseNameList.add(courseName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }
    public void handleScoreListButton(MouseEvent actionEvent) throws IOException {
        FXMLLoader scorelistloader = new FXMLLoader(getClass().getResource("/scorelist.fxml"));
        Parent scorelistroot = scorelistloader.load();


        Scene scorelistScene = new Scene(scorelistroot);
        scorelistScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
        Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Screen secondScreen = Screen.getScreens().stream()
                .filter(screen -> !screen.equals(Screen.getPrimary()))
                .findFirst()
                .orElse(Screen.getPrimary());

        System.out.println("scorelistScene"+scorelistScene);


        Screen screen = Screen.getPrimary();
        Rectangle2D secondScreenBounds = secondScreen.getVisualBounds();

        currentStage.setAlwaysOnTop(true);
        currentStage.setScene(scorelistScene);
        currentStage.setTitle("ScoreList");
    }

    public void handleOnlineButton(MouseEvent mouseEvent) throws IOException {
        FXMLLoader onlineloader = new FXMLLoader(getClass().getResource("/onlinewaiting.fxml"));
        Parent onlineroot = onlineloader.load();

        OnlineController onlineController = onlineloader.getController();
        Scene onlineScene = new Scene(onlineroot);
        onlineScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        onlineController.setUnits(currentStage);
        Screen secondScreen = Screen.getScreens().stream()
                .filter(screen -> !screen.equals(Screen.getPrimary()))
                .findFirst()
                .orElse(Screen.getPrimary());

        System.out.println("onlineScene"+onlineScene);


        Screen screen = Screen.getPrimary();
        Rectangle2D secondScreenBounds = secondScreen.getVisualBounds();

        currentStage.setAlwaysOnTop(true);
        currentStage.setScene(onlineScene);
        currentStage.setTitle("Online waiting");
    }
}
