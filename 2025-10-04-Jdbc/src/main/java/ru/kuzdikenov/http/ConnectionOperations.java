package ru.kuzdikenov.http;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ConnectionOperations {
    public static void setHeaders(HttpURLConnection connection, Map<String, String> headers) {
        for (String key: headers.keySet()) {
            connection.setRequestProperty(key, headers.get(key));
        }
    }

    public static String setParams(String url, Map<String, String> params) {
        // append to the line some params
        // for example url = https://api.example.com/search
        // result = https://api.example.com/search?search=python+tutorial&page=1&limit=10

        StringBuilder sb = new StringBuilder(url);
        if (params.size() > 0) {
            Boolean firstParam = true;
            for (String key: params.keySet()) {
                sb.append(firstParam ? "?" : "&");
                firstParam = false;
                sb.append(URLEncoder.encode(key)).append("=")
                        .append(URLEncoder.encode(params.get(key)));
            }
        }

        return sb.toString();

    }


    public static String readResponse(HttpURLConnection connection) {
        if (connection == null) {
            return null;
        } else {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static HttpURLConnection makeConnectionWithHeaders(String url, HttpMethods httpMethod, Map<String, String> headers) {
        HttpURLConnection connection;
        try {
            URL urlRes = new URL(url);
            connection = (HttpURLConnection) urlRes.openConnection();
            connection.setRequestMethod(httpMethod.getValue());
            ConnectionOperations.setHeaders(connection, headers);
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(15000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    private static void sendRequestData(HttpURLConnection connection, Map<String, String> data) {
        String jsonInput;
        // collecting data to json
        try (OutputStream outputStream = connection.getOutputStream()) {
            jsonInput = new ObjectMapper().writeValueAsString(data);
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addDataToConnection(HttpURLConnection connection, Map<String, String> data) {
        connection.setDoOutput(true);

        ConnectionOperations.sendRequestData(connection, data);

    }

    public static String makeConnectionAndGetResult(String url, HttpMethods method, Map<String, String> headers, Map<String, String> paramsOrData){
        String urlRes = url;
        if (method.equals(HttpMethods.GET)) {
            urlRes = ConnectionOperations.setParams(url, paramsOrData);
        }

        HttpURLConnection connection = ConnectionOperations.makeConnectionWithHeaders(
                urlRes, method, headers);

        // Wrote no !GET so that you can add other methods later
        if (method.equals(HttpMethods.POST) || method.equals(HttpMethods.PUT) || method.equals(HttpMethods.DELETE)) {
            ConnectionOperations.addDataToConnection(connection, paramsOrData);
        }

        try {
            connection.getResponseMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String result = null;
        try {
            result = ConnectionOperations.readResponse(connection);
        } finally {
            connection.disconnect();
        }

        return result;
    }
}
