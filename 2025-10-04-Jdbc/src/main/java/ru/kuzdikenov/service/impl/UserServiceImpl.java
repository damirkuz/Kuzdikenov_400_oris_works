package ru.kuzdikenov.service.impl;

import ru.kuzdikenov.dao.UserDao;
import ru.kuzdikenov.dao.impl.UserDaoImpl;
import ru.kuzdikenov.dto.UserDto;
import ru.kuzdikenov.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(
                user -> new UserDto(user.getName(), user.getLogin())
        ).toList();
    }
}
