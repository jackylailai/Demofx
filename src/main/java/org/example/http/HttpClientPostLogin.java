package org.example.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static org.example.netty.server.NettyClient.localhostip;

public class HttpClientPostLogin {
    public static String sendLoginRequest(String username, String password) {
        String apiUrl = "http://"+localhostip+":8080/user/login";
        String requestBody = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(requestBody);
                outputStream.flush();
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if(responseCode==401){
                return "401";
            }
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "500";
        }
    }
}