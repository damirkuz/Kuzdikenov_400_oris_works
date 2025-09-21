package ru.kpfu.itis.kuzdikenov.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static ru.kpfu.itis.kuzdikenov.http.ConnectionOperations.makeConnectionAndGetResult;


public class HttpClientImpl implements HttpClient{

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        return makeConnectionAndGetResult(url, HttpMethods.GET, headers, params);
//        // get
//        HttpURLConnection connection = ConnectionOperations.makeConnectionWithHeaders(
//                ConnectionOperations.setParams(url, params), HttpMethods.GET, headers);
//
//        String result = ConnectionOperations.readResponse(connection);
//
//        connection.disconnect();
//
//        return result;
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        return makeConnectionAndGetResult(url, HttpMethods.POST, headers, data);
//        // post
//
//        HttpURLConnection connection = ConnectionOperations.makeConnectionWithHeaders(
//                url, HttpMethods.POST, headers);
//
//        ConnectionOperations.addDataToConnection(connection, data);
//
//        String result = ConnectionOperations.readResponse(connection);
//
//        connection.disconnect();
//
//        return result;

    }

    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data) {
        return makeConnectionAndGetResult(url, HttpMethods.PUT, headers, data);
//        HttpURLConnection connection = ConnectionOperations.makeConnectionWithHeaders(
//                url, HttpMethods.PUT, headers);
//
//        ConnectionOperations.addDataToConnection(connection, data);
//
//        String result = ConnectionOperations.readResponse(connection);
//
//        connection.disconnect();
//
//        return result;
    }

    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> data) {
        return makeConnectionAndGetResult(url, HttpMethods.DELETE, headers, data);
//        HttpURLConnection connection = ConnectionOperations.makeConnectionWithHeaders(
//                url, HttpMethods.DELETE, headers);
//
//        ConnectionOperations.addDataToConnection(connection, data);
//
//        String result = ConnectionOperations.readResponse(connection);
//
//        connection.disconnect();
//
//        return result;
    }
}
