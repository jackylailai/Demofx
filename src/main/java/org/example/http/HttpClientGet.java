package org.example.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpClientGet {
    public static HttpURLConnection sendGetRequest(String serverUrl) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(serverUrl);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

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

            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
