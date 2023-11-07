package org.example.modelDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class MissionDTO implements Serializable {

    private String classIndex;

    private String courseIndex;

    private String unitIndex;

    private String teamList;

    public String getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(String classIndex) {
        this.classIndex = classIndex;
    }

    public String getCourseIndex() {
        return courseIndex;
    }

    public void setCourseIndex(String courseIndex) {
        this.courseIndex = courseIndex;
    }

    public String getUnitIndex() {
        return unitIndex;
    }

    public void setUnitIndex(String unitIndex) {
        this.unitIndex = unitIndex;
    }

    public String getTeamList() {
        return teamList;
    }

    public void setTeamList(String teamList) {
        this.teamList = teamList;
    }

}
