package ru.kuzdikenov.service;

import ru.kuzdikenov.dto.UserDto;
import ru.kuzdikenov.entity.User;
import ru.kuzdikenov.exceptions.UserAlreadyExistsInDatabase;
import ru.kuzdikenov.exceptions.UserNotFoundInDatabase;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    void signUp(String name, String lastname, String login, String password) throws UserAlreadyExistsInDatabase;
    void signUp(String name, String lastname, String login, String password, String profilePicturePath) throws UserAlreadyExistsInDatabase;
    boolean loginPassCheck(String login, String password);
    boolean loginExistsCheck(String login);
    User getByLogin(String login) throws UserNotFoundInDatabase;

}
