//package org.example;
//
//import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class Ssss extends Application {
//    @Override
//    public void start(Stage primaryStage) {
//        GridPane root = new GridPane();
//        root.setAlignment(Pos.CENTER);
//        List<String> dataList = Arrays.asList("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"); // 您的数据
//        root.setMinSize(600, 600);
//        for (int i = 0; i < dataList.size(); i++) {
//            Button button = new Button(dataList.get(i));
//
//            // 创建HBox和VBox以实现水平和垂直居中
//            HBox hBox = new HBox(button);
//            hBox.setAlignment(Pos.CENTER);
//            hBox.setSpacing(30); // 设置水平间距
////            vBox.setSpacing(30); // 设置垂直间距
//            VBox vBox = new VBox(hBox);
//            vBox.setAlignment(Pos.CENTER);
//            hBox.setSpacing(30); // 设置水平间距
//            vBox.setSpacing(30); // 设置垂直间距
//            // 添加vBox到GridPane的单元格
//            root.add(vBox, i % 3, i / 3);
//        }
//
//        Scene scene = new Scene(root, 800, 800);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("按钮在Grid单元格中居中示例");
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}