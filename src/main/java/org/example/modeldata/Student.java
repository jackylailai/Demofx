package org.example.modeldata;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.List;

public class Student {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty grade = new SimpleStringProperty();
    private final StringProperty score = new SimpleStringProperty();
    private final StringProperty totalTest = new SimpleStringProperty();
    private final StringProperty correct = new SimpleStringProperty();

    private final StringProperty mission = new SimpleStringProperty();
    private final StringProperty date = new SimpleStringProperty();
    private final StringProperty information = new SimpleStringProperty();

    private List<LearningHistoryEntry> learningHistory;

    public Student(String name, String id, String grade, String score, String totalTest, String correct, String mission, String date, String information) {
        this.name.set(name);
        this.id.set(id);
        this.grade.set(grade);
        this.score.set(score);
        this.totalTest.set(totalTest);
        this.correct.set(correct);
        this.mission.set(mission);
        this.date.set(date);
        this.information.set(information);
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getGrade() {
        return grade.get();
    }

    public StringProperty gradeProperty() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade.set(grade);
    }

    public String getScore() {
        return score.get();
    }

    public StringProperty scoreProperty() {
        return score;
    }

    public void setScore(String score) {
        this.score.set(score);
    }

    public String getTotalTest() {
        return totalTest.get();
    }

    public StringProperty totalTestProperty() {
        return totalTest;
    }

    public void setTotalTest(String totalTest) {
        this.totalTest.set(totalTest);
    }

    public String getCorrect() {
        return correct.get();
    }

    public StringProperty correctProperty() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct.set(correct);
    }

    public String getMission() {
        return mission.get();
    }

    public StringProperty missionProperty() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission.set(mission);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getInformation() {
        return information.get();
    }

    public StringProperty informationProperty() {
        return information;
    }

    public void setInformation(String information) {
        this.information.set(information);
    }

    public List<LearningHistoryEntry> getLearningHistory() {
        return learningHistory;
    }

    public void setLearningHistory(List<LearningHistoryEntry> learningHistory) {
        this.learningHistory = learningHistory;
    }
}
