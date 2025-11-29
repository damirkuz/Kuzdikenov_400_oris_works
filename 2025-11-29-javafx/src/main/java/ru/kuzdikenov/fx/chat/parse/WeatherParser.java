package ru.kuzdikenov.fx.chat.parse;

import org.json.JSONObject;

public class WeatherParser {

    public String formatWeather(String jsonString) {
        JSONObject obj = new JSONObject(jsonString);

        String city = obj.getString("name");

        JSONObject main = obj.getJSONObject("main");
        double temp = main.getDouble("temp");
        double feelsLike = main.getDouble("feels_like");

        String description = obj.getJSONArray("weather")
                .getJSONObject(0)
                .getString("description");


        // 6. Формируем красивую строку
        return String.format(
                """
                        \nГород: %s
                        Погода: %s
                        Температура: %.1f°C (ощущается как %.1f°C)""",
                city, description, temp, feelsLike
        );
    }
}
