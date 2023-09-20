package org.example.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
@Accessors(chain = true)
public class Unit implements Serializable {
    private Long id;
    private Long courseId;

    private Long unitId;
    private String unitName;
    private String unitSchedule;
    private String unitSubject;
    private int unitOrder;
    private String descTitle1;
    private String descContent1;

    private String descTitle2;

    private String descContent2;
    private String videoUrl;
    private String videoFormat;
    private String dfcsFilename;
    private int creditUnits;

    private String pictureUrl1;
    private String pictureUrl2;
    private String pictureUrl3;
    private String pictureUrl4;

    private int state;
    private long longDate;
    private Date createDate;
    private Date updateDate;
    private String studentListId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitSchedule() {
        return unitSchedule;
    }

    public void setUnitSchedule(String unitSchedule) {
        this.unitSchedule = unitSchedule;
    }

    public String getUnitSubject() {
        return unitSubject;
    }

    public void setUnitSubject(String unitSubject) {
        this.unitSubject = unitSubject;
    }

    public int getUnitOrder() {
        return unitOrder;
    }

    public void setUnitOrder(int unitOrder) {
        this.unitOrder = unitOrder;
    }

    public String getDescTitle1() {
        return descTitle1;
    }

    public void setDescTitle1(String descTitle1) {
        this.descTitle1 = descTitle1;
    }

    public String getDescContent1() {
        return descContent1;
    }

    public void setDescContent1(String descContent1) {
        this.descContent1 = descContent1;
    }

    public String getDescTitle2() {
        return descTitle2;
    }

    public void setDescTitle2(String descTitle2) {
        this.descTitle2 = descTitle2;
    }

    public String getDescContent2() {
        return descContent2;
    }

    public void setDescContent2(String descContent2) {
        this.descContent2 = descContent2;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoFormat() {
        return videoFormat;
    }

    public void setVideoFormat(String videoFormat) {
        this.videoFormat = videoFormat;
    }

    public String getDfcsFilename() {
        return dfcsFilename;
    }

    public void setDfcsFilename(String dfcsFilename) {
        this.dfcsFilename = dfcsFilename;
    }

    public int getCreditUnits() {
        return creditUnits;
    }

    public void setCreditUnits(int creditUnits) {
        this.creditUnits = creditUnits;
    }

    public String getPictureUrl1() {
        return pictureUrl1;
    }

    public void setPictureUrl1(String pictureUrl1) {
        this.pictureUrl1 = pictureUrl1;
    }

    public String getPictureUrl2() {
        return pictureUrl2;
    }

    public void setPictureUrl2(String pictureUrl2) {
        this.pictureUrl2 = pictureUrl2;
    }

    public String getPictureUrl3() {
        return pictureUrl3;
    }

    public void setPictureUrl3(String pictureUrl3) {
        this.pictureUrl3 = pictureUrl3;
    }

    public String getPictureUrl4() {
        return pictureUrl4;
    }

    public void setPictureUrl4(String pictureUrl4) {
        this.pictureUrl4 = pictureUrl4;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getStudentListId() {
        return studentListId;
    }

    public void setStudentListId(String studentListId) {
        this.studentListId = studentListId;
    }
}

