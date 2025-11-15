package ru.kuzdikenov.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "Authentication")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpSession session = ((HttpServletRequest) request).getSession(false);
//        if (session == null && (!((HttpServletRequest) request).getRequestURI().contains("login"))) {
//            ((HttpServletResponse) response).sendRedirect("/signUp");
//        } else {
//            chain.doFilter(request, response);
//        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
