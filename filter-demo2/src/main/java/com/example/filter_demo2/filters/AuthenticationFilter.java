package com.example.filter_demo2.filters;

import java.io.IOException;

// import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component 
public class AuthenticationFilter implements Filter {

    @Override 
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

                String token = httpServletRequest.getHeader("token");

                if(token == null || !token.equals("12345")) {
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                    httpServletResponse.setContentType("application/json");
                    httpServletResponse.getWriter().write(
                        "{\r\n" + //
                                                        "    \"message\": \"Authentication is required\"\r\n" + //
                                                        "}"
                    );
                    return;
                }

                filterChain.doFilter(servletRequest, servletResponse);
    }

}
