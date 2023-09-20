package org.example.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CourseParseTest {

    @Test
    public void test() throws JsonProcessingException {
        String target = "[{\"id\":1,\"courseId\":1,\"courseType\":1,\"courseName\":\"安裝教學\",\"courseSchedule\":\"20分鐘\",\"courseDesc\":\"安裝軟體所需的其他輔助套件\",\"creditUnits\":2,\"state\":0,\"longDate\":1694668108329,\"createDate\":\"2023-09-14T05:08:28.364+00:00\",\"updateDate\":\"2023-09-14T05:08:28.364+00:00\",\"studentList\":null},{\"id\":2,\"courseId\":2,\"courseType\":1,\"courseName\":\"操作實作\",\"courseSchedule\":\"30分鐘\",\"courseDesc\":\"實際演練操作流程\",\"creditUnits\":6,\"state\":0,\"longDate\":1694668169895,\"createDate\":\"2023-09-14T05:09:29.895+00:00\",\"updateDate\":\"2023-09-14T05:09:29.895+00:00\",\"studentList\":null},{\"id\":3,\"courseId\":3,\"courseType\":2,\"courseName\":\"軟體介紹\",\"courseSchedule\":\"30分鐘\",\"courseDesc\":\"介紹軟體的發展方向及內容\",\"creditUnits\":6,\"state\":0,\"longDate\":1694668208412,\"createDate\":\"2023-09-14T05:10:08.412+00:00\",\"updateDate\":\"2023-09-14T05:10:08.412+00:00\",\"studentList\":null},{\"id\":4,\"courseId\":4,\"courseType\":2,\"courseName\":\"軟體教學\",\"courseSchedule\":\"60分鐘\",\"courseDesc\":\"透過此次教學，更清楚如何實作此系統\",\"creditUnits\":12,\"state\":0,\"longDate\":1694668239021,\"createDate\":\"2023-09-14T05:10:39.022+00:00\",\"updateDate\":\"2023-09-14T05:10:39.022+00:00\",\"studentList\":null},{\"id\":5,\"courseId\":5,\"courseType\":1,\"courseName\":\"整合應用\",\"courseSchedule\":\"60分鐘\",\"courseDesc\":\"整合應用描述\",\"creditUnits\":12,\"state\":0,\"longDate\":1695094583988,\"createDate\":\"2023-09-19T03:36:24.026+00:00\",\"updateDate\":\"2023-09-19T03:36:24.026+00:00\",\"studentList\":null},{\"id\":6,\"courseId\":6,\"courseType\":1,\"courseName\":\"信號偵蒐測向\",\"courseSchedule\":\"45分鐘\",\"courseDesc\":\"信號偵蒐測向描述\",\"creditUnits\":9,\"state\":0,\"longDate\":1695094674363,\"createDate\":\"2023-09-19T03:37:54.363+00:00\",\"updateDate\":\"2023-09-19T03:37:54.363+00:00\",\"studentList\":null}]";
        String s = "{\"id\":1,\"courseId\":1,\"courseType\":1,\"courseName\":\"安裝教學\",\"courseSchedule\":\"20分鐘\",\"courseDesc\":\"安裝軟體所需的其他輔助套件\",\"creditUnits\":2,\"state\":0,\"longDate\":1694668108329,\"createDate\":\"2023-09-14T05:08:28.364+00:00\",\"updateDate\":\"2023-09-14T05:08:28.364+00:00\",\"studentList\":null}";
        ObjectMapper ob = new ObjectMapper();
//        Course[] courses = ob.readValue(target, Course[].class);//array
//        List<String> sourseNameList = Arrays.stream(courses).map(Course::getCourseName).toList();//
//        System.out.println("aaaaaaaaaaaaaa"+sourseNameList);
        Course c = ob.readValue(s, Course.class);
        log.info("result: {}", c);
    }
}
