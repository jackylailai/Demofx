package org.example.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.example.Main.*;
import static org.example.controller.TsController.jsonNodeForUser;
import static org.example.controller.UnitController.unitsData;
import static org.example.sikulix.TestDFCS.screenshotMap;

public class ScoreController {
    private static String thirdFilename;
    public ImageView screenshot;
    public Label label3;
    public Label subtitle;
    public Label label2;
    public Label label4;
    public Label label1;
    private static Stage newCurrentStage;

    public void initialize(Stage currentStage) {
        newCurrentStage=currentStage;
        String screenshotName = jsonNodeForUser.get("name").asText() + "UnitFirst" + getUnitIdSafely();
        String filename = screenshotMap.get(screenshotName);
        String screenshotThirdName = jsonNodeForUser.get("name").asText() + "UnitThird" + getUnitIdSafely();
        thirdFilename = screenshotMap.get(screenshotThirdName);

        subtitle.setFont(customFontForMiddle);
        label1.setFont(customFontForMiddle);
        label2.setFont(customFontForMiddle);
        label3.setFont(customFontForMiddle);
        label4.setFont(customFontForMiddle);
        try {
            if (filename != null) {
                String imagePath = "file:" + filename;
                System.out.println(imagePath);
                Image image = new Image(filename);
                screenshot.setImage(image);
            } else {
                throw new NullPointerException("尚未考試或找不到相關截圖");
            }
        } catch (NullPointerException e) {
            showWarningAlert();
        }
    }

    private Long getUnitIdSafely() {
        if (unitsData == null || unitsData.isEmpty()) {
            showWarningAlert();
        }
        return unitsData.get(0).getUnitId();
    }

    private void showWarningAlert() {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("警告");
            alert.setHeaderText("發生錯誤");
            alert.setContentText("尚未考試或找不到相關截圖");

            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(newCurrentStage);

            alert.showAndWait();
        });
    }

    public void handleGoBackButtonAction(MouseEvent mouseEvent) {
        try {
            FXMLLoader scorelistloader = new FXMLLoader(getClass().getResource("/scorelist.fxml"));
            Parent scorelistroot = scorelistloader.load();


            Scene scorelistScene = new Scene(scorelistroot);
            scorelistScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/globalStyles.css")).toExternalForm());
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            Screen secondScreen = Screen.getScreens().stream()
                    .filter(screen -> !screen.equals(Screen.getPrimary()))
                    .findFirst()
                    .orElse(Screen.getPrimary());

            System.out.println("scorelistScene" + scorelistScene);


            Screen screen = Screen.getPrimary();
            Rectangle2D secondScreenBounds = secondScreen.getVisualBounds();

            currentStage.setAlwaysOnTop(true);
            currentStage.setScene(scorelistScene);
            currentStage.setTitle("ScoreList");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleOutputButtonAction(MouseEvent mouseEvent) {
        String pdfFilePath = "C:\\serverdb\\準則鑑測簽證卡.pdf";

        File pdfFile = new File(pdfFilePath);

        if (pdfFile.exists()) {
            try {
                Desktop.getDesktop().browse(pdfFile.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("指定的PDF文件不存在。");
        }
    }

    public void handleDeleteSignalButtonAction(MouseEvent mouseEvent) {
        String imagePath = "file:" + thirdFilename;
        System.out.println(imagePath);
        Image image = new Image(thirdFilename);
        screenshot.setImage(image);
    }
}
