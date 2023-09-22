package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
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
import org.example.modaldata.CourseButtonData;
import org.example.vo.Course;
import org.example.vo.Unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.controller.TsController.jsonNodeForUser;

public class CourseItemController {

    @FXML
    private Button button1;
    public static String buttonString1;
    //對應的button 設定static string
    //沒有資料才call api
    //元素交給scene scene有button要動作 有button
    //static可用來暫時存放記憶體
    public static List<Course> coursedata;
    @FXML
    private Button button2;
    private static String buttonString2;
    @FXML
    private Button button3;
    private static String buttonString3;
    @FXML
    private Button button4;
    private static String buttonString4;
    @FXML
    private Button button5;
    private static String buttonString5;
    @FXML
    private Button button6;
    private static String buttonString6;
    @FXML
    public Button button7;
    private static String buttonString7;
    @FXML
    public Button button8;
    private static String buttonString8;

    public void setCourses(List<Course> courses) {
        if (courses != null && courses.size() >= 2) {
            CourseButtonData buttonData1 = createCourseData(courses.get(0).getCourseId(),courses.get(0).getCourseName());
            coursedata = courses;
            button1.setText(courses.get(0).getCourseName());
            button1.setUserData(buttonData1);

            button2.setText(courses.get(1).getCourseName());
            CourseButtonData buttonData2 = createCourseData(courses.get(1).getCourseId(),courses.get(1).getCourseName());
            button2.setUserData(buttonData2);

            button3.setText(courses.get(2).getCourseName());
            CourseButtonData buttonData3 = createCourseData(courses.get(2).getCourseId(),courses.get(2).getCourseName());
            button3.setUserData(buttonData3);

            button4.setText(courses.get(3).getCourseName());
            CourseButtonData buttonData4 = createCourseData(courses.get(3).getCourseId(),courses.get(3).getCourseName());
            button4.setUserData(buttonData4);

            button5.setText(courses.get(4).getCourseName());
            CourseButtonData buttonData5 = createCourseData(courses.get(4).getCourseId(),courses.get(4).getCourseName());
            button5.setUserData(buttonData5);

            button6.setText(courses.get(5).getCourseName());
            CourseButtonData buttonData6 = createCourseData(courses.get(5).getCourseId(),courses.get(5).getCourseName());
            button6.setUserData(buttonData6);
        }
    }

    private CourseButtonData createCourseData(Long courseId, String courseName) {
        return new CourseButtonData(courseId,courseName);
    }


    @FXML
    public void handleButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        CourseButtonData buttonData = (CourseButtonData) clickedButton.getUserData();
        Long courseId = buttonData.getCourseId();
        String courseName = buttonData.getCourseName();
        System.out.println("CourseName"+courseName);
        if (courseId != null) {
            showCourseDetails(courseId,event);
        } else {
            System.out.println("使用者可能未點擊");
        }
    }

    private void showCourseDetails(Long courseId, ActionEvent actionevent) {
        String baseUrl = "http://localhost:8080/course";
        String serverUrl = baseUrl + "/" + courseId;
        try {

            String jsonResponse = HttpClientGetData.sendGetRequest(serverUrl);
            if (jsonResponse!=null) {
                List<Unit> units = parseUnitsJson(jsonResponse);

                FXMLLoader unitloader = new FXMLLoader(getClass().getResource("/unitlist.fxml"));
                Parent unitroot = unitloader.load();

                UnitListController unitListController = unitloader.getController();
                unitListController.setUnits(units);

                Scene unitScene = new Scene(unitroot);
                Stage currentStage = (Stage) ((Node) actionevent.getSource()).getScene().getWindow();


                currentStage.setScene(unitScene);
                currentStage.setTitle("Unit List");
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Unit> parseUnitsJson(String jsonResponse) {
        List<Unit> units = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            if (jsonNode.isArray()) {
                for (JsonNode unitJson : jsonNode) {
                    Unit unit = new Unit();
                    unit.setCourseId(unitJson.get("courseId").asLong());
                    unit.setUnitId(unitJson.get("unitId").asLong());
                    unit.setUnitName(unitJson.get("name").asText());
                    unit.setUnitSubject(unitJson.get("unitSubject").asText());
                    units.add(unit);
                    System.out.println("units" + units);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return units;
    }


    @FXML
    public void handleGoBackButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ts.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            TsController tsController = loader.getController();
            tsController.initializeUserData(jsonNodeForUser);

            currentStage.setScene(scene);
            currentStage.setTitle("TS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

