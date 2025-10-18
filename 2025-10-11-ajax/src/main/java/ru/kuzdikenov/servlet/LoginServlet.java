package ru.kuzdikenov.servlet;


import ru.kuzdikenov.entity.User;
import ru.kuzdikenov.exceptions.UserNotFoundInDatabase;
import ru.kuzdikenov.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    public static UserService userService;

    @Override
    public void init() {
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.loginPassCheck(login, password)) {
            // logic to authenticate user

            // session
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", login);
            httpSession.setMaxInactiveInterval(60 * 60);

            // cookie
            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(24 * 60 * 60);

            resp.addCookie(cookie);

            try {
                User user = userService.getByLogin(login);
                req.setAttribute("profilePictureUrl", user.getProfilePicturePath());
            } catch (UserNotFoundInDatabase e) {
                throw new RuntimeException(e);
            }



            sendInfoAboutUserSession(req, resp);
        } else {
            req.setAttribute("error", "Неправильный логин или пароль");
            req.getRequestDispatcher("login.ftl").forward(req, resp);
        }
    }

    private void sendInfoAboutUserSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        Cookie[] cookies = req.getCookies();
        String cookieUser = "";
        String sessionId = "";

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("user".equalsIgnoreCase(c.getName())) {
                    cookieUser = c.getValue();
                } else if ("jsessionid".equalsIgnoreCase(c.getName())) {
                    sessionId = c.getValue();
                }
            }
        } else {
            sessionId = httpSession.getId();
        }
        req.setAttribute("sessionUser", (String) httpSession.getAttribute("user"));
        req.setAttribute("cookieUser", cookieUser);
        req.setAttribute("sessionId", sessionId);
        req.getRequestDispatcher("main.ftl").forward(req, resp);
    }

}
