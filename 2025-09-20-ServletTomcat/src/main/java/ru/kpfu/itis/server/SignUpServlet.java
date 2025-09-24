package ru.kpfu.itis.server;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "SignUp", urlPatterns = "/signUp")
public class SignUpServlet extends HttpServlet {

    public static Map<String, String> accounts = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("signUp.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // registration

        // TODO: persist in memory (Map) login + password and after that use it in LoginServlet instead of "login" and "password"

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        accounts.put(login, password);

        resp.sendRedirect("/login");
    }
}
