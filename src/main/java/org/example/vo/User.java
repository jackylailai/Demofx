package org.example.vo;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;

@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
@Accessors(chain = true)
public class User implements Serializable{
    private Long id;
    private String name;
    private String username;
    private String password;
    private String oAuthKey;
    private Long studentId;
    private int studentBatch;
    private int grade;
    private String studentUnit;
    private int level;
    private String ip;
    private long longDate;
    private Date createTime;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getoAuthKey() {
        return oAuthKey;
    }

    public void setoAuthKey(String oAuthKey) {
        this.oAuthKey = oAuthKey;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public int getStudentBatch() {
        return studentBatch;
    }

    public void setStudentBatch(int studentBatch) {
        this.studentBatch = studentBatch;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getLongDate() {
        return longDate;
    }

    public void setLongDate(long longDate) {
        this.longDate = longDate;
    }

    public Date getCreateDate() {
        return createTime;
    }

    public void setCreateDate(Date createDate) {
        this.createTime = createDate;
    }

    public Date getUpdateDate() {
        return updateTime;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateTime = updateDate;
    }
    public void setGrade(int grade) {
        this.grade = grade;
    }
    public int getGrade(){
        return grade;
    }
}
