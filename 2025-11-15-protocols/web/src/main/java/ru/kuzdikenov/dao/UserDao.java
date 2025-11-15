package ru.kuzdikenov.dao;

import ru.kuzdikenov.entity.User;
import ru.kuzdikenov.exceptions.UserAlreadyExistsInDatabase;
import ru.kuzdikenov.exceptions.UserNotFoundInDatabase;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    void save(User user) throws UserAlreadyExistsInDatabase;

    User getByLogin(String login) throws UserNotFoundInDatabase;


}
