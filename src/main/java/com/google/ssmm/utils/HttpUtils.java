package com.google.ssmm.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpUtils {
    public static String getResp(String apiURL) {
        StringBuilder responseBuilder = new StringBuilder();

        try {
            // Create a URL object with the API URL
            URL url = new URL(apiURL);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept-Charset", "gb2312");

            // Set the request method to GET (or POST, PUT, DELETE, etc. if needed)
            connection.setRequestMethod("GET");
            connection.setReadTimeout(1000);


            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gb2312"));

            String line;
            int i=0;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            // Get the response body as a String
            String responseBody = responseBuilder.toString();

            // Print or process the response body as needed
            System.out.println("Response Body:");
            System.out.println(responseBody);

            // Disconnect the connection
            connection.disconnect();
            return responseBody;
        } catch (IOException e) {

            // Get the response body as a String
            String responseBody = responseBuilder.toString();

            // Print or process the response body as needed
            System.out.println("Response Body:");
            System.out.println(responseBody);

            // Disconnect the connection
            return responseBody;
        }
    }


}
