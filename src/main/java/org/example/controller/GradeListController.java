package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.example.modeldata.Student;

import java.io.IOException;
import java.util.Objects;


import static org.example.controller.AnswerController.correctAnswerCount;
import static org.example.controller.TsController.jsonNodeForUser;

public class GradeListController {
//    @FXML private TableView<Student> table;
//    @FXML private TableColumn<Student,String> nameColumn;
//    @FXML private TableColumn<Student,String> idColumn;
//    @FXML private TableColumn<Student,String> taskColumn;
//    @FXML private TableColumn<Student,String> scoreColumn;
//
//
//
//    private ObservableList<Student> getStudents(){
//        ObservableList<Student> characters = FXCollections.observableArrayList();
//        characters.add(new Student("Cersei","Lannister","Queen Regent","100000"));
//        characters.add(new Student("Jaime","Lannister","King Slayer","8000"));
//        characters.add(new Student("Tyrion","Lannister","Queen's Hand","60000"));
//
//        return characters;
//    }
//
//
//    public void initialize() {
//        //nameColumn
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//
//        //idColumn
//        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
//
//        //taskColumn
//        taskColumn.setCellValueFactory(new PropertyValueFactory<>("task"));
//
//        //scoreColumn
//        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
//
//        //table = new TableView<>();
//        table.setItems(getStudents());
//
//    }
    @FXML
    private TableView<Student> tableView;

    public void initialize() {
        // Create columns (if not already defined in FXML)
        TableColumn<Student, String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Student, String> idColumn = new TableColumn<>("學號");
        TableColumn<Student, String> taskColumn = new TableColumn<>("任務");
        TableColumn<Student, String> scoreColumn = new TableColumn<>("成績");
        TableColumn<Student, String> totalTestColumn = new TableColumn<>("總共題目");
        TableColumn<Student, String> correctColumn = new TableColumn<>("答對題目");

        nameColumn.setPrefWidth(120);
        idColumn.setPrefWidth(120);
        taskColumn.setPrefWidth(120);
        scoreColumn.setPrefWidth(120);
        totalTestColumn.setPrefWidth(120);
        correctColumn.setPrefWidth(120);

        nameColumn.setResizable(false);
        idColumn.setResizable(false);
        taskColumn.setResizable(false);
        scoreColumn.setResizable(false);
        totalTestColumn.setResizable(false);
        correctColumn.setResizable(false);


// Associate columns with data properties (if not already defined in FXML)
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("task"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        totalTestColumn.setCellValueFactory(new PropertyValueFactory<>("totalTest"));
        correctColumn.setCellValueFactory(new PropertyValueFactory<>("correct"));

// Set up cell factories to allow editing (if needed)
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        taskColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        scoreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        totalTestColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        correctColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        String name = jsonNodeForUser.get("name").asText();
//        String info = "學員 :   "+name;
// Add columns to the TableView
        tableView.getColumns().addAll(nameColumn, idColumn, taskColumn, scoreColumn,totalTestColumn, correctColumn);

        System.out.println(correctAnswerCount);
        String counts = Integer.toString(correctAnswerCount);
// Create data for the TableView
        ObservableList<Student> data = FXCollections.observableArrayList(
                new Student(name, "67890", "信號搜索", "無","7",counts),
                new Student(name, "67890", "信號追蹤", "無","2",""),
                new Student(name, "67890", "信號監視", "無","2","")
        );
// Set the data to the TableView
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click detection
                Student selectedItem = tableView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    // Perform navigation or open a new screen based on the selected item
                    System.out.println("Selected: " + selectedItem);
                    // You can open a new window or scene here
                }
            }
        });
        tableView.setItems(data);
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

            System.out.println("scorelistScene"+scorelistScene);


            Screen screen = Screen.getPrimary();
            Rectangle2D secondScreenBounds = secondScreen.getVisualBounds();

            currentStage.setAlwaysOnTop(true);
            currentStage.setScene(scorelistScene);
            currentStage.setTitle("ScoreList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
