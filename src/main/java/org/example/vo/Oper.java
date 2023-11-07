package org.example.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.Date;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Oper {
    private Long id;
    private Long operId;
    private Long unitId;
    private Long contentId;
    private String title;
    private String titleCh;
    private String answer;
    private Integer group1;
    private Integer group2;
    private Integer group3;
    private Integer group4;
    private String pictureName;
    private int state;
    private long longDate;
    private Date createTime;
    private Date updateTime;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleCh() {
        return titleCh;
    }

    public void setTitleCh(String titleCh) {
        this.titleCh = titleCh;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getGroup1() {
        return group1;
    }

    public void setGroup1(Integer group1) {
        this.group1 = group1;
    }

    public Integer getGroup2() {
        return group2;
    }

    public void setGroup2(Integer group2) {
        this.group2 = group2;
    }

    public Integer getGroup3() {
        return group3;
    }

    public void setGroup3(Integer group3) {
        this.group3 = group3;
    }

    public Integer getGroup4() {
        return group4;
    }

    public void setGroup4(Integer group4) {
        this.group4 = group4;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getLongDate() {
        return longDate;
    }

    public void setLongDate(long longDate) {
        this.longDate = longDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}