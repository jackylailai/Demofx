package org.example.modeldata;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;

public class LearningHistoryEntry {
    private final StringProperty course;
    private final StringProperty date;
    @Getter
    private boolean qualified;
    private final StringProperty information;

    public LearningHistoryEntry(String course, String date, boolean qualified, String information) {
        this.course = new SimpleStringProperty(course);
        this.date = new SimpleStringProperty(date);
        this.qualified = qualified;
        this.information = new SimpleStringProperty(information);
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

    public String getCourse() {
        return course.get();
    }

    public StringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
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

    public void setQualified(boolean qualified) {
        this.qualified = qualified;
    }
}
