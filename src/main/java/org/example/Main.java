package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.controller.LoginController;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
        Parent root = fxmlLoader.load();

        LoginController loginController = fxmlLoader.getController();
        loginController.setPrimaryStage(primaryStage);
        System.out.println("primary stage"+primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SSTP Demo");
        primaryStage.show();
    }
}