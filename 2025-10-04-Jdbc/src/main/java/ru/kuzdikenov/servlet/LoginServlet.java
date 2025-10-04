package ru.kuzdikenov.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Login", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("login.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");


        if (SignUpServlet.accounts.containsKey(login) && SignUpServlet.accounts.get(login).equalsIgnoreCase(password)) {
            // logic to authenticate user

            // session
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user", login);
            httpSession.setMaxInactiveInterval(60 * 60);

            // cookie
            Cookie cookie = new Cookie("user", login);
            cookie.setMaxAge(24 * 60 * 60);

            resp.addCookie(cookie);

            sendInfoAboutUserSession(req, resp);

        } else {
            resp.sendRedirect("/login");
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
