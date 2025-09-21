package http;

import org.junit.Assert;
import org.junit.Test;
import ru.kpfu.itis.http.ConnectionOperations;

import java.util.HashMap;
import java.util.Map;


public class TestConnectionOperations {

    @Test
    public void testSetParams() {
        String example = "https://api.example.com/search";
        String expected = "https://api.example.com/search?search=python+tutorial&limit=10&page=1";
        Map<String, String> params = new HashMap<>();
        params.put("search", "python tutorial");
        params.put("page", "1");
        params.put("limit", "10");
        String result = ConnectionOperations.setParams(example, params);
        Assert.assertEquals(expected, result);

    }


}
