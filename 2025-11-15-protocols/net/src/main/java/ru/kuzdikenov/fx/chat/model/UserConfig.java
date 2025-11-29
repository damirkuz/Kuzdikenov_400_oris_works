package ru.kuzdikenov.fx.chat.model;

public record UserConfig(
        String username,
        String host,
        int port
) {
}
