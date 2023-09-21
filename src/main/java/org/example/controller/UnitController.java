package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.example.modaldata.UnitButtonData;
import org.example.vo.Unit;

import java.io.IOException;
import java.util.List;

public class UnitController {
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    public void handleButtonAction(ActionEvent actionEvent) {
    }
    @FXML
    public void setUnits(List<Unit> units) {
        System.out.println("傳進unit頁面"+units);
        if (units != null) {
            System.out.println("傳進unit頁面"+units);
            button1.setText(units.get(0).getDescContent1());
            button1.setUserData(units.get(0).getUnitId());
            button2.setText(units.get(0).getDescContent2());
            button2.setUserData(units.get(0).getUnitId());
            button3.setText(units.get(0).getDescContent3());
            button3.setUserData(units.get(0).getUnitId());

//            UnitButtonData buttonData1 = createUnitData(units.get(0).getUnitId(),units.get(0).getUnitName());
//            button1.setText(units.get(0).getUnitName());
//            button1.setUserData(buttonData1);
//
//            button2.setText(units.get(1).getUnitName());
//            UnitButtonData buttonData2 = createUnitData(units.get(1).getUnitId(),units.get(1).getUnitName());
//            button2.setUserData(buttonData2);
//
//            button3.setText(units.get(2).getUnitName());
//            UnitButtonData buttonData3 = createUnitData(units.get(2).getUnitId(),units.get(2).getUnitName());
//            button3.setUserData(buttonData3);

        }
    }

    @FXML
    public void handleGoBackButtonAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/unitlist.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            currentStage.setScene(scene);
            currentStage.setTitle("Unit List");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
