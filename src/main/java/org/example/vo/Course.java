package org.example.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Course {

    private Long id;
    private Long courseId;
    private int courseType;
    private String courseName;
    private String courseSchedule;
    private String courseDesc;
    private int creditUnits;
    private int state;
    private long longDate;
    private Date createDate;
    private Date updateDate;
    private String studentList;
    // Getters and setters
}
