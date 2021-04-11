package com.tui.proof.security;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (!request.getRequestURI().equalsIgnoreCase("/logging")) {
            String authorization = request.getHeader("Authorization");
            if (authorization == null) {
                // For simple the test, accept all the text that have the header in Authorization key
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "You must be logged!");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

}
