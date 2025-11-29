package ru.kuzdikenov.fx.chat.service;

import org.json.JSONObject;
import ru.kuzdikenov.fx.chat.model.Command;
import ru.kuzdikenov.fx.chat.model.CommandAction;
import ru.kuzdikenov.fx.chat.model.CommandResult;
import ru.kuzdikenov.fx.chat.parse.WeatherParser;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class CommandService {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String exchangeApiUrl = "https://open.er-api.com/v6/latest/";
    private final String weatherApiUrl = "http://api.openweathermap.org/data/2.5/weather";
    private final String weatherApiKey = System.getenv("OPEN_WEATHER_API_KEY");

    private final WeatherParser weatherParser = new WeatherParser();


    public CommandResult handle(String message) {
        if (!message.startsWith("/")) {
            return showMessage(message);
        }

        for (Command command: Command.values()) {
            if (message.startsWith(command.getWord())) {
                return handleCommand(command, message);
            }
        }

        return showMessage("Неизвестная команда, напишите /list чтобы узнать доступные");
    }

    private CommandResult showMessage(String message) {
        return new CommandResult(CommandAction.SHOW_MESSAGE, message);
    }

    public CommandResult handleCommand(Command command, String message) {
        return switch (command) {
            case LIST -> {
                StringBuilder sb = new StringBuilder();
                for (Command com: Command.values()) {
                    sb.append("\n");
                    sb.append(com.getWord()).append(" ").append(com.getDescription());
                }
                yield showMessage(sb.toString());
            }
            case QUIT -> {
                yield new CommandResult(CommandAction.EXIT, "");
            }
            case WEATHER -> {
                yield showMessage(getWeatherIn(message.split(" ")[1]));
            }
            case EXCHANGE -> {
                yield showMessage(getExchangeOn(message.split(" ")[1]));
            }
        };
    }

    public String getWeatherIn(String city) {

        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

        String url = weatherApiUrl +
                "?q=" + encodedCity +
                "&appid=" + weatherApiKey +
                "&units=metric" +
                "&lang=ru";

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());
            return weatherParser.formatWeather(jsonObject.toString());
        } catch (IOException | InterruptedException e) {
            return "В окно посмотри";
        }
    }

    public String getExchangeOn(String currency) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(exchangeApiUrl + currency)).build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonObject = new JSONObject(response.body());
            String toRub;
            try {
                toRub = Double.toString(jsonObject.getJSONObject("rates").getDouble("RUB"));
            } catch (Exception e) {
                toRub = "без понятия вообще";
            }

            return currency + " в рублях: " + toRub;
        } catch (IOException | InterruptedException e) {
            return currency + " в рублях: без понятия вообще";
        }
    }
}
