package org.example.modeldata;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty id = new SimpleStringProperty();
    private final StringProperty task = new SimpleStringProperty();
    private final StringProperty score = new SimpleStringProperty();
    private final StringProperty totalTest = new SimpleStringProperty();
    private final StringProperty correct = new SimpleStringProperty(); // 修改为字符串类型

    public Student(String name, String id, String task, String score, String totalTest, String correct) { // 修改为字符串类型
        this.name.set(name);
        this.id.set(id);
        this.task.set(task);
        this.score.set(score);
        this.totalTest.set(totalTest);
        this.correct.set(correct);
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

    public String getTask() {
        return task.get();
    }

    public StringProperty taskProperty() {
        return task;
    }

    public void setTask(String task) {
        this.task.set(task);
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
}