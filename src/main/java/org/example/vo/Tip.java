package org.example.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
public class Tip implements Serializable {
    private Long id;
    private Long tipId;
    private Long unitId;
    private Long contentId;

    private String title;

    private String content;
    private int state;
    private long longDate;

    private Date createDate;
    private Date updateDate;
    // Getter and Setter methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTipId() {
        return tipId;
    }

    public void setTipId(Long tipId) {
        this.tipId = tipId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
