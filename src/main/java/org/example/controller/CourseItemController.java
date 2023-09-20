package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.http.HttpClientGetData;
import org.example.vo.Course;

import java.io.IOException;
import java.util.List;

public class CourseItemController {
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    public void setCourses(List<Course> courses) {
        if (courses != null && courses.size() >= 6) {

            button1.setText(courses.get(0).getCourseName());
            button1.setUserData(courses.get(0).getCourseId());
            button1.setText(courses.get(1).getCourseName());
            button1.setUserData(courses.get(1).getCourseId());
            button1.setText(courses.get(2).getCourseName());
            button1.setUserData(courses.get(2).getCourseId());
            button1.setText(courses.get(3).getCourseName());
            button1.setUserData(courses.get(3).getCourseId());
            button1.setText(courses.get(4).getCourseName());
            button1.setUserData(courses.get(4).getCourseId());
            button1.setText(courses.get(5).getCourseName());
            button1.setUserData(courses.get(5).getCourseId());
            button1.setText(courses.get(6).getCourseName());
            button1.setUserData(courses.get(6).getCourseId());
//            button1.setText(courses.get(0));
//            button2.setText(courses.get(1));
//            button3.setText(courses.get(2));
//            button4.setText(courses.get(3));
//            button5.setText(courses.get(4));
//            button6.setText(courses.get(5));
        }
    }
    @FXML
    public void handleButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String courseId = (String) clickedButton.getUserData();
        if (courseId != null) {
//            showCourseDetails(courseId);
        } else {
            System.out.println("使用者可能未點擊");
        }
    }

//    private void showCourseDetails(String courseId) {
//        String baseUrl = "http://localhost:8080/course";
//        String serverUrl = baseUrl + "/" + courseId;
//        try {
//
//            String jsonResponse = HttpClientGetData.sendGetRequest(serverUrl);
//            ObjectMapper objectMapper = new ObjectMapper();
//            if (jsonResponse!=null) {
//
//                List<Unit> units = parseUnitsJson(jsonResponse);
//
//                FXMLLoader unitloader = new FXMLLoader(getClass().getResource("/unitlist.fxml"));
//                Parent unitroot = unitloader.load();
//
//                UnitController unitController = unitloader.getController();
//                unitController.setCourses(units);
//
//
//
//                Scene unitScene = new Scene(unitroot);
//                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
//                currentStage.setScene(unitScene);
//                currentStage.setTitle("Unit");
//            } else {
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }



    @FXML
    public void handleGoBackButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ts.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            currentStage.setScene(scene);
            currentStage.setTitle("ES");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

