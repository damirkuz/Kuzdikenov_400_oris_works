package ru.kuzdikenov.service;

import ru.kuzdikenov.dto.UserDto;
import ru.kuzdikenov.exceptions.UserAlreadyExistsInDatabase;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    void signUp(String name, String lastname, String login, String password) throws UserAlreadyExistsInDatabase;
    boolean loginPassCheck(String login, String password);
    boolean loginExistsCheck(String login);
}
