package ru.kuzdikenov.app;

import ru.kuzdikenov.dao.UserDao;
import ru.kuzdikenov.dao.impl.UserDaoImpl;
import ru.kuzdikenov.service.UserService;
import ru.kuzdikenov.service.impl.UserServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);
        sce.getServletContext().setAttribute("userService", userService);
    }


}
