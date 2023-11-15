package org.example.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.vo.Course;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HttpClientGetData {
    public static String sendGetRequest(String serverUrl) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(serverUrl);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            String sessionId = "JSESSIONID=" + SessionStorage.getSessionId();
            connection.setRequestProperty("Cookie" , sessionId);

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("Response Data: " + response.toString());

//            ObjectMapper ob = new ObjectMapper();
//            Course[] courses = ob.readValue(response.toString(), Course[].class);//array
//            List<String> sourseNameList = Arrays.stream(courses).map(Course::getCourseName).toList();//
//            System.out.println("aaaaaaaaaaaaaa"+sourseNameList);

            connection.disconnect();
            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverUrl;
    }
}