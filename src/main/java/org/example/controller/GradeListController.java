package org.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.example.modaldata.Student;

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

        nameColumn.setPrefWidth(180);
        idColumn.setPrefWidth(180);
        taskColumn.setPrefWidth(180);
        scoreColumn.setPrefWidth(180);

        nameColumn.setResizable(false);
        idColumn.setResizable(false);
        taskColumn.setResizable(false);
        scoreColumn.setResizable(false);


// Associate columns with data properties (if not already defined in FXML)
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("task"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

// Set up cell factories to allow editing (if needed)
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        taskColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        scoreColumn.setCellFactory(TextFieldTableCell.forTableColumn());

// Add columns to the TableView
        tableView.getColumns().addAll(nameColumn, idColumn, taskColumn, scoreColumn);

// Create data for the TableView
        ObservableList<Student> data = FXCollections.observableArrayList(
                new Student("Jacky", "12345", "信號搜索", "95"),
                new Student("Jacky", "67890", "信號追蹤", "88"),
                new Student("Jacky", "11111", "信號監視", "75")
        );
// Set the data to the TableView
        tableView.setItems(data);
    }
}
