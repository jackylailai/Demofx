package org.example.modelDTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class MissionDTO implements Serializable {

    private String classIndex;

    private String courseIndex;

    private String unitIndex;

    private String teamList;

}
