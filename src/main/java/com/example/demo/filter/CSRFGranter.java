package com.example.demo.filter;

import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CSRFGranter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        CsrfToken csrf = (CsrfToken) servletRequest.getAttribute(CsrfToken.class.getName());
        String token = csrf.getToken();
        if (token != null && isAuthenticating(servletRequest)) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Cookie cookie = new Cookie("CSRF-TOKEN", token);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean isAuthenticating(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return request.getRequestURI().equals("/greet/login");
    }

    @Override
    public void destroy() {}
}
