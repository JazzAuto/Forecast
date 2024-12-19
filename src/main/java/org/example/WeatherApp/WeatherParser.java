package org.example.WeatherApp;

import org.json.JSONObject;

public class WeatherParser
{

    public String parseWeather(String jsonResponse)
    {
        JSONObject obj = new JSONObject(jsonResponse);
        JSONObject main = obj.getJSONObject("main");
        double temp = main.getDouble("temp");
        return "Temperature: " + temp + "K";
    }
}
