package org.example.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Content implements Serializable {
    private Long id;
    private Long unitId;
    private Long contentId;
    private String content;
    private int contentOrder;

    private int state;
    private long longDate;
    private Date createDate;
    private Date updateDate;
    public Content() {
        this.longDate = System.currentTimeMillis();
    }
    // Getters and setters
    // Constructors
}

