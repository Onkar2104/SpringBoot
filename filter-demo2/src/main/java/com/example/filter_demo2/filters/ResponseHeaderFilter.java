package com.example.filter_demo2.filters;

import java.io.IOException;
import java.util.UUID;

// import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
// import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component 
public class ResponseHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

                // HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

                String requestID = UUID.randomUUID().toString();

                httpServletResponse.setHeader("x-request-id: ", requestID);

                filterChain.doFilter(servletRequest, servletResponse);
    }

}
