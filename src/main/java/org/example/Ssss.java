//package org.example;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Ssss extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        primaryStage.setTitle("GridPane Example");
//
//        List<String> imagePaths = new ArrayList<>();
//        imagePaths.add("file:dfcs.jpg");
//        imagePaths.add("file:dfcs.jpg");
//        imagePaths.add("file:///c:/SSTP/demo/videos/dfcs.jpg");
//        imagePaths.add("file:dfcs.jpg");
//        imagePaths.add("file:///c:/SSTP/demo/videos/dfcs.jpg");
//        imagePaths.add("file:///c:/SSTP/demo/videos/dfcs.jpg");
//        imagePaths.add("file:///c:/SSTP/demo/videos/dfcs.jpg");
//
//        int gridSize = calculateGridSize(imagePaths.size());
//
//        GridPane gridPane = new GridPane();
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//
//        for (int i = 0; i < gridSize; i++) {
//            for (int j = 0; j < gridSize; j++) {
//                int index = i * gridSize + j;
//                if (index < imagePaths.size()) {
//                    Button button = createButton(imagePaths.get(index));
//                    gridPane.add(button, j, i);
//                }
//            }
//        }
//
//        Scene scene = new Scene(gridPane, 400, 400);
//        primaryStage.setScene(scene);
//        primaryStage.show();
//    }
//
//    private Button createButton(String imagePath) {
//        Image image = new Image(imagePath);
//        ImageView imageView = new ImageView(image);
//        imageView.setFitWidth(100);
//        imageView.setFitHeight(100);
//        Button button = new Button();
//        button.setGraphic(imageView);
//        return button;
//    }
//
//    private int calculateGridSize(int listSize) {
//        int gridSize = (int) Math.ceil(Math.sqrt(listSize));
//        return gridSize % 3 == 0 ? gridSize : calculateGridSize(listSize + 1);
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
