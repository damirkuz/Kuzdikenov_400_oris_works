package ru.kuzdikenov.fx.chat.model;

public enum Command {
    LIST ("/list", "Показывает список доступных команд"),
    WEATHER ("/weather", "Показывает погоду в каком-то городе. Пример: /weather Казань"),
    EXCHANGE ("/exchange", "Показывает курс в какой-то валюты. Пример: /exchange usd"),
    QUIT ("/quit", "Выходит в главное меню");

    private final String word;
    private final String description;


    Command(String word, String description) {
        this.word = word;
        this.description = description;
    }

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }
}
