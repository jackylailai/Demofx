package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.http.HttpClientGetData;
import org.example.modaldata.CourseLabelData;
import org.example.vo.Course;
import org.example.vo.Unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.controller.TsController.jsonNodeForUser;

public class CourseItemController {

    public Label label9;
    public Label label8;
    public Label label7;
    public Label label6;
    public Label label5;
    public Label label4;
    public Label label3;
    public Label label2;
    public Label label1;
    @FXML
    private ImageView button9;

    public static String buttonString1;
    //對應的button 設定static string
    //沒有資料才call api
    //元素交給scene scene有button要動作 有button
    //static可用來暫時存放記憶體
    @FXML
    private ImageView button1;
    public static List<Course> coursedata;
    public static String courseNameTitle;
    @FXML
    private ImageView button2;
    private static String buttonString2;
    @FXML
    private ImageView button3;
    private static String buttonString3;
    @FXML
    private ImageView button4;
    private static String buttonString4;
    @FXML
    private ImageView button5;
    @FXML
    private ImageView button6;
    @FXML
    private ImageView button7;

    @FXML
    private ImageView button8;

//把圖片改成BUTTON比較好 然後label獨立拉出來寫上去
    public void setCourses(List<Course> courses) {
        if (courses != null && courses.size() >= 2) {
            CourseLabelData labelData1 = createCourseData(courses.get(0).getCourseId(),courses.get(0).getCourseName());
            coursedata = courses;
            label1.setText(courses.get(0).getCourseName());
            button1.setUserData(labelData1);

            label2.setText(courses.get(1).getCourseName());
            CourseLabelData labelData2 = createCourseData(courses.get(1).getCourseId(),courses.get(1).getCourseName());
            button2.setUserData(labelData2);

            label3.setText(courses.get(2).getCourseName());
            CourseLabelData labelData3 = createCourseData(courses.get(2).getCourseId(),courses.get(2).getCourseName());
            button3.setUserData(labelData3);

            label4.setText(courses.get(3).getCourseName());
            CourseLabelData labelData4 = createCourseData(courses.get(3).getCourseId(),courses.get(3).getCourseName());
            button4.setUserData(labelData4);

            label5.setText(courses.get(4).getCourseName());
            CourseLabelData labelData5 = createCourseData(courses.get(4).getCourseId(),courses.get(4).getCourseName());
            button5.setUserData(labelData5);

            label6.setText(courses.get(5).getCourseName());
            CourseLabelData labelData6 = createCourseData(courses.get(5).getCourseId(),courses.get(5).getCourseName());
            button6.setUserData(labelData6);
        }
    }
    public void initialize() {

//        courseTraining.setOnMouseClicked(event -> {
//            ActionEvent actionEvent = new ActionEvent(event.getSource(), event.getTarget());
//            handleCourseTrainingClick(actionEvent);
//        });

    }
    private CourseLabelData createCourseData(Long courseId, String courseName) {
        return new CourseLabelData(courseId,courseName);
    }


    @FXML
    public void handleButtonAction(MouseEvent event) {
//        System.out.println("被點擊"+event);
        ImageView clickedButton = (ImageView) event.getSource();
        System.out.println("clickButton"+clickedButton);
        CourseLabelData labelData = (CourseLabelData) clickedButton.getUserData();
        System.out.println("labeldata"+labelData);
        Long courseId = labelData.getCourseId();
        String courseName = labelData.getCourseName();
        courseNameTitle = courseName;
        System.out.println("CourseName"+courseName);
        if (courseId != null) {
            System.out.println("courseid"+courseId);
            showCourseDetails(courseId,event);
        } else {
            System.out.println("使用者可能未點擊");
        }
    }

    private void showCourseDetails(Long courseId, MouseEvent event) {
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
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                unitScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());

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
    public void handleGoBackButtonAction(MouseEvent actionEvent) {
        try {
            System.out.println("返回上一頁(主畫面)");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ts.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
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

