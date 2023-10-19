package org.example.modeldata;

public class CourseLabelData {
    private Long courseId;
    private String courseName;

    public CourseLabelData(Long courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }
}