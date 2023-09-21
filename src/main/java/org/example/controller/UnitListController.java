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
import org.example.modaldata.UnitButtonData;
import org.example.vo.Unit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnitListController {
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
    @FXML
    public Button button7;
    @FXML
    public Button button8;

    public void setUnits(List<Unit> units) {
        if (units != null && units.size() >= 2) {
//            button1.setText(units.get(0).getUnitName());
//            button1.setUserData(units.get(0).getUnitId());
//            button2.setText(units.get(1).getUnitName());
//            button2.setUserData(units.get(1).getUnitId());
//            button3.setText(units.get(2).getUnitName());
//            button3.setUserData(units.get(2).getUnitId());
//            button4.setText(units.get(3).getUnitName());
//            button4.setUserData(units.get(3).getUnitId());
            UnitButtonData buttonData1 = createUnitData(units.get(0).getUnitId(),units.get(0).getUnitName());
            button1.setText(units.get(0).getUnitName());
            button1.setUserData(buttonData1);

            button2.setText(units.get(1).getUnitName());
            UnitButtonData buttonData2 = createUnitData(units.get(1).getUnitId(),units.get(1).getUnitName());
            button2.setUserData(buttonData2);

            button3.setText(units.get(2).getUnitName());
            UnitButtonData buttonData3 = createUnitData(units.get(2).getUnitId(),units.get(2).getUnitName());
            button3.setUserData(buttonData3);

            button4.setText(units.get(3).getUnitName());
            UnitButtonData buttonData4 = createUnitData(units.get(3).getUnitId(),units.get(3).getUnitName());
            button4.setUserData(buttonData4);

//            button5.setText(units.get(4).getUnitName());
//            UnitButtonData buttonData5 = createUnitData(units.get(4).getUnitId(),units.get(4).getUnitName());
//            button5.setUserData(buttonData5);
//
//            button6.setText(units.get(5).getUnitName());
//            UnitButtonData buttonData6 = createUnitData(units.get(5).getUnitId(),units.get(5).getUnitName());
//            button6.setUserData(buttonData6);
        }
    }
    private UnitButtonData createUnitData(Long unitId, String unitName) {
        return new UnitButtonData(unitId,unitName);
    }
    @FXML
    public void handleButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        UnitButtonData buttonData = (UnitButtonData) clickedButton.getUserData();
        Long unitId = (Long) buttonData.getUnitId();
        String unitName = buttonData.getUnitName();
        System.out.println("unitName"+unitName);
        if (unitId != null) {
            showUnitDetails(unitId,event);
        } else {
            System.out.println("使用者可能未點擊");
        }
    }
    
    private void showUnitDetails(Long courseId, ActionEvent actionevent) {
        String baseUrl = "http://localhost:8080/unit";
        String serverUrl = baseUrl + "/" + courseId;
        try {

            String jsonResponse = HttpClientGetData.sendGetRequest(serverUrl);
            if (jsonResponse!=null) {
                System.out.println("jsonResponse進入parse前"+jsonResponse);
                List<Unit> units = parseUnitsJson(jsonResponse);
                System.out.println("units parse後"+units);
                FXMLLoader unitloader = new FXMLLoader(getClass().getResource("/unit.fxml"));
                Parent unitroot = unitloader.load();

                UnitController unitController = unitloader.getController();
                unitController.setUnits(units);

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
            JsonNode unitJson = objectMapper.readTree(jsonResponse);
            //少掉列表迴圈
            Unit unit = new Unit();
            unit.setCourseId(unitJson.get("courseId").asLong());
            unit.setUnitId(unitJson.get("unitId").asLong());
            unit.setUnitId(unitJson.get("name").asLong());
            unit.setUnitSubject(unitJson.get("unitSubject").asText());
            unit.setDescContent1(unitJson.get("descContent1").asText());
            unit.setDescContent2(unitJson.get("descContent2").asText());
            unit.setDescContent3(unitJson.get("descContent3").asText());
            unit.setPictureUrl1(unitJson.get("pictureUrl1").asText());
            units.add(unit);
            System.out.println("units" + unit);

//            if (jsonNode.isArray()) {
//                for (JsonNode unitJson : jsonNode) {
//                    Unit unit = new Unit();
//                    unit.setCourseId(unitJson.get("courseId").asLong());
//                    unit.setUnitId(unitJson.get("unitId").asLong());
//                    unit.setUnitId(unitJson.get("name").asLong());
//                    unit.setUnitSubject(unitJson.get("unitSubject").asText());
//                    unit.setDescContent1(unitJson.get("descContent1").asText());
//                    unit.setDescContent2(unitJson.get("descContent2").asText());
//                    unit.setDescContent3(unitJson.get("descContent3").asText());
//                    unit.setPictureUrl1(unitJson.get("pictureUrl1").asText());
//                    units.add(unit);
//                    System.out.println("units" + units);
//                }

        } catch(Exception e){
            e.printStackTrace();
        }
        return units;
    }
    @FXML
    public void handleGoBackButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/courseitem.fxml"));
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
