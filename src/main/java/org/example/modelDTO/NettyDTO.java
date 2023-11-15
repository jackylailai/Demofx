package org.example.modelDTO;

import lombok.Data;

import java.io.Serializable;
@Data
public class NettyDTO implements Serializable {
    private String username;

    private String name;

    private Long level;

    private Long grade;

    private String studentUnit;

    private String ip;

    private Long studentId;

    private Long studentBatch;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public String getStudentUnit() {
        return studentUnit;
    }

    public void setStudentUnit(String studentUnit) {
        this.studentUnit = studentUnit;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentBatch() {
        return studentBatch;
    }

    public void setStudentBatch(Long studentBatch) {
        this.studentBatch = studentBatch;
    }
}
