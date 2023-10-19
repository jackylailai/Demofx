package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static org.example.Main.customFontForAll;
import static org.example.controller.TsController.jsonNodeForUser;

public class ScoreListController {
    public Label scorelist;
    public Label scoredetails;

    public void initialize(){
        scorelist.setText("成績單列表");
        scorelist.setFont(Font.font(customFontForAll.getFamily(), 24));
        scoredetails.setText("成績詳情");
        scoredetails.setFont(Font.font(customFontForAll.getFamily(), 24));
    }
    public void handleGoBackButtonAction(MouseEvent mouseEvent) {
        try {
            System.out.println("返回上一頁(主畫面)");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ts.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            TsController tsController = loader.getController();
            tsController.initializeUserData(jsonNodeForUser);

            currentStage.setScene(scene);
            currentStage.setTitle("TS");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleGoScore(MouseEvent event) {
//        ImageView clickedButton = (ImageView) event.getSource();
//        UnitLabelData buttonData = (UnitLabelData) clickedButton.getUserData();
//        Long scoreId = (Long) buttonData.getUnitId();
//        String scoreName = buttonData.getUnitName();
//        System.out.println("scoreName"+scoreName);
//        if (scoreId != null) {
            showUnitDetails(event);
//        } else {
//            System.out.println("使用者可能未點擊");
//        }
    }

    private void showUnitDetails(MouseEvent event) {
//        String baseUrl = "http://localhost:8080/score";
//        String serverUrl = baseUrl + "/" + courseId;
        try {

//            String jsonResponse = HttpClientGetData.sendGetRequest(serverUrl);
//            if (jsonResponse!=null) {
//                System.out.println("jsonResponse進入parse前"+jsonResponse);
//                List<Unit> scores = parseUnitsJson(jsonResponse);
//                System.out.println("scores parse後"+scores);
                FXMLLoader scoreloader = new FXMLLoader(getClass().getResource("/score.fxml"));
                Parent scoreroot = scoreloader.load();

                ScoreController scoreController = scoreloader.getController();
//                scoreController.setScores(scores);

                Scene scoreScene = new Scene(scoreroot);
                scoreScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                System.out.println("scoreScene"+scoreScene);

                currentStage.setScene(scoreScene);
                currentStage.setTitle("Score");
//            } else {
//
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleGoGradeList(MouseEvent mouseEvent) throws IOException {
        FXMLLoader gradelistloader = new FXMLLoader(getClass().getResource("/gradelist.fxml"));
        Parent gradelistroot = gradelistloader.load();

        GradeListController gradelistController = gradelistloader.getController();
//                scoreController.setScores(scores);

        Scene gradelistScene = new Scene(gradelistroot);
        gradelistScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
        gradelistScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/tableStyles.css")).toExternalForm());
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();


        System.out.println("gradelist"+gradelistScene);

        currentStage.setScene(gradelistScene);
        currentStage.setTitle("Gradelist");

    }
//    private List<Unit> parseUnitsJson(String jsonResponse) {
//        List<Unit> units = new ArrayList<>();
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            JsonNode unitJson = objectMapper.readTree(jsonResponse);
//            //少掉列表迴圈
//            Unit unit = new Unit();
//            unit.setCourseId(unitJson.get("courseId").asLong());
//            unit.setUnitId(unitJson.get("unitId").asLong());
//            System.out.println("解析unitjson"+unitJson.get("unitId").asLong());
//            unit.setUnitSubject(unitJson.get("unitSubject").asText());
//            unit.setDescContent1(unitJson.get("descContent1").asText());
//            unit.setDescContent2(unitJson.get("descContent2").asText());
//            unit.setDescContent3(unitJson.get("descContent3").asText());
//            unit.setPictureUrl1(unitJson.get("pictureUrl1").asText());
//            unit.setPictureUrl2(unitJson.get("pictureUrl2").asText());
//            units.add(unit);
//            System.out.println("units" + unit);
//
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//        return units;
//    }
}
