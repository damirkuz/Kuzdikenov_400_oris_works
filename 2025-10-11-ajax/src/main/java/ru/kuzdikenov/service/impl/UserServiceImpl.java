package ru.kuzdikenov.service.impl;

import ru.kuzdikenov.dao.UserDao;
import ru.kuzdikenov.dao.impl.UserDaoImpl;
import ru.kuzdikenov.dto.UserDto;
import ru.kuzdikenov.entity.User;
import ru.kuzdikenov.exceptions.UserAlreadyExistsInDatabase;
import ru.kuzdikenov.exceptions.UserNotFoundInDatabase;
import ru.kuzdikenov.service.UserService;
import ru.kuzdikenov.util.PasswordUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(
                user -> new UserDto(user.getName(), user.getLogin())
        ).toList();
    }

    public void signUp(String name, String lastname, String login, String password) throws UserAlreadyExistsInDatabase {
        User user = new User(name, lastname, login, password);
        userDao.save(user);
    }

    @Override
    public boolean loginPassCheck(String login, String password){
        User user;
        try {
            user = userDao.getByLogin(login);
        } catch (UserNotFoundInDatabase e) {
            // user isn't found -> login or pass is wrong
            return false;
        }

        return isUserPassword(user, password);
    }

    @Override
    public boolean loginExistsCheck(String login) {
        User user;
        try {
            user = userDao.getByLogin(login);
        } catch (UserNotFoundInDatabase e) {
            // user isn't found -> login or pass is wrong
            return false;
        }

        return true;
    }


    private boolean isUserPassword(User user, String password) {
        return user.getPassword().equals(PasswordUtil.encrypt(password));
    }


}
