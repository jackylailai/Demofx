package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.modeldata.LearningHistoryEntry;
import org.example.modeldata.Student;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import static org.example.controller.AnswerController.correctAnswerCount;
import static org.example.controller.QuizController.testTime;
import static org.example.controller.TsController.jsonNodeForUser;
import static org.example.controller.UnitController.informationDetail;

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
        TableColumn<Student, String> idColumn = new TableColumn<>("單位");
        TableColumn<Student, String> gradeColumn = new TableColumn<>("級職");
        TableColumn<Student, String> missionColumn = new TableColumn<>("任務");
        TableColumn<Student, String> scoreColumn = new TableColumn<>("合格");
        TableColumn<Student, String> totalTestColumn = new TableColumn<>("總共題目");
        TableColumn<Student, String> correctColumn = new TableColumn<>("答對題目");
        TableColumn<Student, String> dateColumn = new TableColumn<>("測驗日期");
        TableColumn<Student, String> informationColumn = new TableColumn<>("資訊");

        nameColumn.setPrefWidth(100);
        idColumn.setPrefWidth(160);
        gradeColumn.setPrefWidth(90);
        missionColumn.setPrefWidth(120);
        scoreColumn.setPrefWidth(70);
        totalTestColumn.setPrefWidth(70);
        correctColumn.setPrefWidth(70);
        dateColumn.setPrefWidth(180);
        informationColumn.setPrefWidth(130);

        nameColumn.setResizable(false);
        idColumn.setResizable(false);
        gradeColumn.setResizable(false);
        missionColumn.setResizable(false);
        scoreColumn.setResizable(false);
        totalTestColumn.setResizable(false);
        correctColumn.setResizable(false);
        dateColumn.setResizable(false);
        informationColumn.setResizable(false);

// Associate columns with data properties (if not already defined in FXML)
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        missionColumn.setCellValueFactory(new PropertyValueFactory<>("mission"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        totalTestColumn.setCellValueFactory(new PropertyValueFactory<>("totalTest"));
        correctColumn.setCellValueFactory(new PropertyValueFactory<>("correct"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        informationColumn.setCellValueFactory(new PropertyValueFactory<>("information"));

// Set up cell factories to allow editing (if needed)
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        gradeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        missionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        scoreColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        totalTestColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        correctColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        dateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        informationColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        String gotest= dateFormat.format(currentDate);
        System.out.println(gotest+":::::testTime");

        String name = jsonNodeForUser.get("name").asText();
        int grade = jsonNodeForUser.get("grade").asInt();
        String studentUnit = jsonNodeForUser.get("studentUnit").asText();
        String gradeName= "";
        String dateTime="";
        if(grade==0){
            gradeName = "中尉";
        } else if (grade==1) {
            gradeName = "少校";
        }
//        String info = "學員 :   "+name;
// Add columns to the TableView
        tableView.getColumns().addAll(nameColumn, idColumn, gradeColumn,missionColumn,scoreColumn,totalTestColumn, correctColumn,dateColumn,informationColumn);

        System.out.println(correctAnswerCount);
        String counts = Integer.toString(correctAnswerCount);
// Create data for the TableView
        ObservableList<Student> data = FXCollections.observableArrayList(
                new Student(name, studentUnit, gradeName,"無紀錄","7",counts,"信號搜索",testTime,informationDetail),
                new Student(name, studentUnit, gradeName,"無紀錄","2","","信號追蹤","",""),
                new Student(name, studentUnit, gradeName,"無紀錄","2","","信號監視","","")
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

//        List<LearningHistoryEntry> history = new ArrayList<>();
//        history.add(new LearningHistoryEntry("未完成", "日期1", true, "號手1"));
//        history.add(new LearningHistoryEntry("未完成", "日期2", false, "號手2"));
//
//        Student student = new Student("Jacky", "67890", "信號搜索", "無", "總共題目", "答對題目");
//        student.setLearningHistory(history);


//        scoreColumn.setCellFactory(new Callback<TableColumn<Student, String>, TableCell<Student, String>>() {
//            @Override
//            public TableCell<Student, String> call(TableColumn<Student, String> param) {
//                return new TableCell<Student, String>() {
//                    @Override
//                    protected void updateItem(String item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (empty) {
//                            System.out.println("item空的"+item);
//                            setGraphic(null);
//                        } else {
//                            System.out.println("item有:::"+item);
//                            Student student = getTableView().getItems().get(getIndex());
//                            List<LearningHistoryEntry> learningHistory = student.getLearningHistory();
//                            System.out.println("上面有LearningHistory:::"+learningHistory);
//                            if (learningHistory != null) {
//                                List<LearningHistoryEntry> validLearningHistory = learningHistory.stream()
//                                        .filter(entry -> entry != null)
//                                        .toList();
//
//                                TableView<LearningHistoryEntry> historyTableView = new TableView<>();
//                                TableColumn<LearningHistoryEntry, String> courseColumn = new TableColumn<>("課程");
//                                TableColumn<LearningHistoryEntry, String> dateColumn = new TableColumn<>("日期");
//                                TableColumn<LearningHistoryEntry, String> qualifiedColumn = new TableColumn<>("是否合格");
//                                TableColumn<LearningHistoryEntry, String> informationColumn = new TableColumn<>("資訊");
//
//                                courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));
//                                dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
//                                qualifiedColumn.setCellValueFactory(new PropertyValueFactory<>("qualified"));
//                                informationColumn.setCellValueFactory(new PropertyValueFactory<>("information"));
//
//                                historyTableView.getColumns().addAll(courseColumn, dateColumn, qualifiedColumn, informationColumn);
//
//                                ObservableList<LearningHistoryEntry> historyData = FXCollections.observableArrayList(validLearningHistory);
//                                historyTableView.setItems(historyData);
//                                System.out.println("有LearningHistory:::"+learningHistory);
//                                setGraphic(historyTableView);
//                            }
//                        }
//                    }
//                };
//            }
//        });
