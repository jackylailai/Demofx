package org.example.vo;

import lombok.Data;

import java.util.Date;

@Data//會自動生成一些通用的方法，包括 getter 和 setter 方法
public class Quiz {
    private Long id;
    private Long quizId;
    private Long unitId;
    private String title;
    private String content;

    private Integer tofQuiz;
    private Integer essayQuiz;
    private String answer;
    private int state;
    private long longDate;
    private Date createDate;
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
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

    public Integer getTofQuiz() {
        return tofQuiz;
    }

    public void setTofQuiz(Integer tofQuiz) {
        this.tofQuiz = tofQuiz;
    }

    public Integer getEssayQuiz() {
        return essayQuiz;
    }

    public void setEssayQuiz(Integer essayQuiz) {
        this.essayQuiz = essayQuiz;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
// Getter and Setter 方法
}