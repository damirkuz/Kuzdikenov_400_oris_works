package ru.kuzdikenov.dto;

public record UserWithIdAndUsernameDto(
        Long id,
        String username
) {
}
