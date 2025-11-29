package ru.kuzdikenov.fx.chat.model;

public record CommandResult (
        CommandAction action,
        String showedMessage
) {
}
