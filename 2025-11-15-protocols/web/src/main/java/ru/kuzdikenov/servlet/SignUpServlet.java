package ru.kuzdikenov.servlet;

import ru.kuzdikenov.dao.impl.UserDaoImpl;
import ru.kuzdikenov.entity.User;
import ru.kuzdikenov.exceptions.UserAlreadyExistsInDatabase;
import ru.kuzdikenov.service.UserService;
import ru.kuzdikenov.service.impl.UserServiceImpl;
import ru.kuzdikenov.util.FormParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@MultipartConfig
@WebServlet(name = "SignUp", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    public static UserService userService;

    @Override
    public void init() {
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("signUp.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // registration
        String name = FormParser.getStringParameter(req, "name");
        String lastname = FormParser.getStringParameter(req, "lastname");
        String login = FormParser.getStringParameter(req, "login");
        String password = FormParser.getStringParameter(req, "password");
        String picturePath = FormParser.parseAndSaveFile(req, "file");

        try {
            userService.signUp(name, lastname, login, password, picturePath);
            resp.sendRedirect("/login");
        } catch (UserAlreadyExistsInDatabase e) {
            req.setAttribute("error", "Пользователь уже существует");
            req.getRequestDispatcher("signUp.ftl").forward(req, resp);

        }
    }
}
