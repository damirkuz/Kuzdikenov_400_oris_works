package ru.kuzdikenov.dao;

import ru.kuzdikenov.entity.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    void save(User user);

    User getById(Integer id);


}
