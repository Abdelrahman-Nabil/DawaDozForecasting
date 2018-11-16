package com.example.abdu.dawadozforecasting;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class Query {

    public static ArrayList<City> citiesResult = new ArrayList<>();
    public static ArrayList<City> extractCity(String url) {

        try {
            citiesResult.clear();
            URL link = createUrl("https://samples.openweathermap.org/data/2.5/forecast?id=524901&APPID={f70c0764ff0fbf5b2a19b45a150e5fda}");
            JSONObject result = new JSONObject(makeHttpRequest(link));
            extractFeatureFromJson(result);

            link = createUrl("https://samples.openweathermap.org/data/2.5/forecast?id=4908066&APPID={f70c0764ff0fbf5b2a19b45a150e5fda}");
            result = new JSONObject(makeHttpRequest(link));
            extractFeatureFromJson(result);

            link = createUrl("https://samples.openweathermap.org/data/2.5/forecast?id=3067696&APPID={f70c0764ff0fbf5b2a19b45a150e5fda}");
            result = new JSONObject(makeHttpRequest(link));
            extractFeatureFromJson(result);

            link = createUrl("https://samples.openweathermap.org/data/2.5/forecast?id=292968&APPID={f70c0764ff0fbf5b2a19b45a150e5fda}");
            result = new JSONObject(makeHttpRequest(link));
            extractFeatureFromJson(result);

            link = createUrl("https://samples.openweathermap.org/data/2.5/forecast?id=4104031&APPID={f70c0764ff0fbf5b2a19b45a150e5fda}");
            result = new JSONObject(makeHttpRequest(link));
            extractFeatureFromJson(result);

            return citiesResult;
        } catch (Exception err) {
            return null;
        }

    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();

            jsonResponse = readFromStream(inputStream);
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {

                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static URL createUrl(String stringUrl) {
        URL url;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            return null;
        }
        return url;
    }

    private static ArrayList<City> extractFeatureFromJson(JSONObject response) {
        try {

            Log.e("I reached extract f", "i reached HERE!");

            City city = new City();
            if (response != null) {

                JSONArray results = response.getJSONArray("list");
                for (int i = 0; i < results.length(); i++) {
                    JSONObject hoursTemp = results.getJSONObject(i);
                    JSONObject main = hoursTemp.getJSONObject("main");
                    Double temp =  main.getDouble("temp");
                    Log.e("temp Val", Double.toString(temp));

                    JSONArray weathCondition = hoursTemp.getJSONArray("weather");
                    JSONObject weathDisrciption = weathCondition.getJSONObject(0);
                    String description = weathDisrciption.getString("description");
                    Log.e("description Val", description);

                    String time = hoursTemp.getString("dt_txt");
                    Log.e("time Val", time);

                    city.addTemperature(temp, description, time);

                }

                JSONObject name = response.getJSONObject("city");
                String cityName = name.getString("name");
                city.setName(cityName);
                citiesResult.add(city);


            }
        } catch (JSONException e) {
            return null;
        }
        return citiesResult;
    }
}
