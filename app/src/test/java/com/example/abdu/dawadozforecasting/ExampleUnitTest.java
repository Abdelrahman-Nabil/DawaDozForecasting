package com.example.abdu.dawadozforecasting;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.example.abdu.dawadozforecasting.Query.createUrl;
import static com.example.abdu.dawadozforecasting.Query.extractFeatureFromJson;
import static com.example.abdu.dawadozforecasting.Query.makeHttpRequest;
import static com.example.abdu.dawadozforecasting.Query.readFromStream;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void QueryTest() throws JSONException {
        JSONObject response = null;
        List<City> actual = extractFeatureFromJson(response);
        assertEquals(null, actual);
    }

    @Test
    public void HttpRequestTest() throws IOException{
        URL url = createUrl("https://google.com"); //this should not return correct JSON
        String JsonResponse = makeHttpRequest(url);
        assertNotEquals(null, JsonResponse);

        url = createUrl("https://samples.openweathermap.org/data/2.5/forecast?id=524901&APPID={f70c0764ff0fbf5b2a19b45a150e5fda}");
        JsonResponse = makeHttpRequest(url);
        assertNotEquals(null, JsonResponse);
    }
    @Test
    public void readStreamTest()  throws IOException{
        URL url = createUrl("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&APPID={f70c0764ff0fbf5b2a19b45a150e5fda}");
        String JsonResponse = makeHttpRequest(url);

        assertEquals("{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":" +
                "[{\"id\":300,\"main\":\"Drizzle\",\"description\":\"light intensity " +
                "drizzle\",\"icon\":\"09d\"}],\"base\":\"stations\",\"main\":{" +
                "\"temp\":280.32,\"pressure\":1012,\"humidity\":81,\"temp_min\":2" +
                "79.15,\"temp_max\":281.15},\"visibility\":10000,\"wind\":{\"speed\":4." +
                "1,\"deg\":80},\"clouds\":{\"all\":90},\"dt\":1485789600,\"sys\":{\"type\":1" +
                ",\"id\":5091,\"message\":0.0103,\"country\":\"GB\",\"sunrise\":1485762037,\"suns" +
                "et\":1485794875},\"id\":2643743,\"name\":\"London\",\"cod\":200}", JsonResponse);


    }
}