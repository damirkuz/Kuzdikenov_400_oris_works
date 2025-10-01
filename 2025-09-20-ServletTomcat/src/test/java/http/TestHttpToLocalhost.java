package http;

import org.junit.Assert;
import org.junit.Test;
import ru.kuzdikenov.http.HttpClient;
import ru.kuzdikenov.http.HttpClientImpl;

import java.util.HashMap;
import java.util.Map;

public class TestHttpToLocalhost {
    @Test
    public void testGetRequestHello() {

        String expected = "Hello";
        String url = "http://localhost:8080/hello";
        Map<String, String> params = new HashMap<>();
        HttpClient httpClient = new HttpClientImpl();
        String actual = httpClient.get(url, new HashMap<>(), params);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPostRequestHello() {
        String expected = "{\"key1\":\"value1\"}";

        String url = "http://localhost:8080/hello";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        HttpClient httpClient = new HttpClientImpl();
        String actual = httpClient.post(url, headers, data);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPutRequest() {
        String expected = "I'm put answer";

        String url = "http://localhost:8080/hello";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> data = new HashMap<>();

        HttpClient httpClient = new HttpClientImpl();
        String actual = httpClient.put(url, headers, data);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testDeleteRequest() {
        String expected = "I'm delete answer";

        String url = "http://localhost:8080/hello";
        Map<String, String> headers = new HashMap<>();
        Map<String, String> data = new HashMap<>();

        HttpClient httpClient = new HttpClientImpl();
        String actual = httpClient.delete(url, headers, data);
        Assert.assertEquals(expected, actual);

    }
}
