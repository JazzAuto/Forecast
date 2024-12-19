package org.example.WeatherApp;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import io.github.cdimascio.dotenv.Dotenv;

import org.json.JSONObject;

public class WeatherService {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String API_KEY = dotenv.get("WEATHER_API_KEY");
    private static final String BASE_URL = "https://api.weatherstack.com/current?access_key=";

    public String getWeather (String city) throws Exception
    {
        String urlString = BASE_URL + API_KEY + "&query=" + city;
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
        {
            content.append(inputLine);
        }
        in.close();

        // Print the full JSON response for debugging
        String jsonResponse = content.toString();
        System.out.println(jsonResponse);

        // Parse the JSON response
        JSONObject obj = new JSONObject(jsonResponse);

        // Check if the "main" key exists
        if (!obj.has("main"))
        {
            throw new Exception("JSONObject['main'] not found");
        }

        JSONObject main = obj.getJSONObject("main");
        double temp = main.getDouble("temp");
        return "Temperature: " + temp + "K";
    }
}
