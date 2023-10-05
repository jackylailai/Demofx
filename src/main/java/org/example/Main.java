package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import org.example.controller.LoginController;

import java.io.InputStream;
import java.util.Objects;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public static Font customFontForAll;
    public static Font customFontForSmall;
    public static Font boldFontForAll;
    public static Font lightFontForAll;
    @Override
    public void start(Stage primaryStage) throws Exception {
        InputStream ttfPath = this.getClass().getResourceAsStream( "/fonts/TaipeiSansTCBeta-Regular.ttf" );
        customFontForAll= Font.loadFont(ttfPath,24);
        customFontForSmall = Font.loadFont(ttfPath,18);
        System.out.println("small,"+customFontForSmall);
        InputStream ttfBoldPath = this.getClass().getResourceAsStream( "/fonts/TaipeiSansTCBeta-Bold.ttf" );
        boldFontForAll= Font.loadFont(ttfBoldPath,24);
        InputStream ttfLightPath = this.getClass().getResourceAsStream( "/fonts/TaipeiSansTCBeta-light.ttf" );
        lightFontForAll= Font.loadFont(ttfLightPath,14);

        primaryStage.setResizable(false);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
//        Parent root = fxmlLoader.load();
//
//        LoginController loginController = fxmlLoader.getController();
//        loginController.setPrimaryStage(primaryStage);
//        System.out.println("primary stage"+primaryStage);
//        Scene scene = new Scene(root);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("SSTP Demo");
//        primaryStage.show();
        Screen secondScreen = Screen.getScreens().stream()
                .filter(screen -> !screen.equals(Screen.getPrimary()))
                .findFirst()
                .orElse(Screen.getPrimary()); // 如果找不到第二個屏幕，則使用主屏幕

        Rectangle2D bounds = secondScreen.getVisualBounds();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = fxmlLoader.load();

        LoginController loginController = fxmlLoader.getController();
        loginController.setPrimaryStage(primaryStage);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        double defaultWidth = 1080;
        double defaultHeight = 960;
        primaryStage.setWidth(defaultWidth);
        primaryStage.setHeight(defaultHeight);

        double centerX = bounds.getMinX() + (bounds.getWidth() - defaultWidth) / 2;
        double centerY = bounds.getMinY() + (bounds.getHeight() - defaultHeight) / 2;

        primaryStage.setX(centerX);
        primaryStage.setY(centerY);

        primaryStage.setTitle("SSTP Demo");
        primaryStage.show();
    }
}