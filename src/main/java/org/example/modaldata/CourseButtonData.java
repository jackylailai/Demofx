package org.example.modaldata;

public class CourseButtonData {
    private Long courseId;
    private String courseName;

    public CourseButtonData(Long courseId, String courseName) {
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