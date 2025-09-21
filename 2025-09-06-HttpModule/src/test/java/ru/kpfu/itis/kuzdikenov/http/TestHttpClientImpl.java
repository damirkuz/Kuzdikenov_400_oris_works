package ru.kpfu.itis.kuzdikenov.http;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class TestHttpClientImpl {
    @Test
    public void testGetRequest1() {

        String expected = "[  {    \"userId\": 1,    \"id\": 1,    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"  },  {    \"userId\": 1,    \"id\": 2,    \"title\": \"qui est esse\",    \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"  },  {    \"userId\": 1,    \"id\": 3,    \"title\": \"ea molestias quasi exercitationem repellat qui ipsa sit aut\",    \"body\": \"et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut\"  },  {    \"userId\": 1,    \"id\": 4,    \"title\": \"eum et est occaecati\",    \"body\": \"ullam et saepe reiciendis voluptatem adipisci\\nsit amet autem assumenda provident rerum culpa\\nquis hic commodi nesciunt rem tenetur doloremque ipsam iure\\nquis sunt voluptatem rerum illo velit\"  },  {    \"userId\": 1,    \"id\": 5,    \"title\": \"nesciunt quas odio\",    \"body\": \"repudiandae veniam quaerat sunt sed\\nalias aut fugiat sit autem sed est\\nvoluptatem omnis possimus esse voluptatibus quis\\nest aut tenetur dolor neque\"  },  {    \"userId\": 1,    \"id\": 6,    \"title\": \"dolorem eum magni eos aperiam quia\",    \"body\": \"ut aspernatur corporis harum nihil quis provident sequi\\nmollitia nobis aliquid molestiae\\nperspiciatis et ea nemo ab reprehenderit accusantium quas\\nvoluptate dolores velit et doloremque molestiae\"  },  {    \"userId\": 1,    \"id\": 7,    \"title\": \"magnam facilis autem\",    \"body\": \"dolore placeat quibusdam ea quo vitae\\nmagni quis enim qui quis quo nemo aut saepe\\nquidem repellat excepturi ut quia\\nsunt ut sequi eos ea sed quas\"  },  {    \"userId\": 1,    \"id\": 8,    \"title\": \"dolorem dolore est ipsam\",    \"body\": \"dignissimos aperiam dolorem qui eum\\nfacilis quibusdam animi sint suscipit qui sint possimus cum\\nquaerat magni maiores excepturi\\nipsam ut commodi dolor voluptatum modi aut vitae\"  },  {    \"userId\": 1,    \"id\": 9,    \"title\": \"nesciunt iure omnis dolorem tempora et accusantium\",    \"body\": \"consectetur animi nesciunt iure dolore\\nenim quia ad\\nveniam autem ut quam aut nobis\\net est aut quod aut provident voluptas autem voluptas\"  },  {    \"userId\": 1,    \"id\": 10,    \"title\": \"optio molestias id quia eum\",    \"body\": \"quo et expedita modi cum officia vel magni\\ndoloribus qui repudiandae\\nvero nisi sit\\nquos veniam quod sed accusamus veritatis error\"  }]";
        String url = "https://jsonplaceholder.typicode.com/posts";
        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        HttpClient httpClient = new HttpClientImpl();
        String actual = httpClient.get(url, new HashMap<>(), params);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetRequest2() {
        String expected = "Понял, принял";
        String url = "https://webhook.site/4e1e2ef3-6813-45b6-ab3f-32076cf6aa9a";
        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        HttpClient httpClient = new HttpClientImpl();
        String actual = httpClient.get(url, new HashMap<>(), params);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testPostRequest() {
        String expected = "Понял, принял";

        String url = "https://webhook.site/4e1e2ef3-6813-45b6-ab3f-32076cf6aa9a";
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
        String expected = "Понял, принял";

        String url = "https://webhook.site/4e1e2ef3-6813-45b6-ab3f-32076cf6aa9a";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Map<String, String> data = new HashMap<>();
        data.put("key2", "value2");

        HttpClient httpClient = new HttpClientImpl();
        String actual = httpClient.put(url, headers, data);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testDeleteRequest() {
        String expected = "Понял, принял";

        String url = "https://webhook.site/4e1e2ef3-6813-45b6-ab3f-32076cf6aa9a";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        Map<String, String> data = new HashMap<>();
        data.put("key3", "value3");

        HttpClient httpClient = new HttpClientImpl();
        String actual = httpClient.delete(url, headers, data);
        Assert.assertEquals(expected, actual);

    }


}
