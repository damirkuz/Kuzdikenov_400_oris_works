package ru.kuzdikenov;

import ru.kuzdikenov.dao.UserDao;
import ru.kuzdikenov.dao.impl.UserDaoImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
//        List<User> users = userDao.getAll().stream().forEach(System.out::println);
    }
}
