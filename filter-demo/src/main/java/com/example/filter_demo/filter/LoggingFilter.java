package com.example.filter_demo.filter;

import java.io.IOException;
import java.util.UUID;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
@Order(2)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

                long startTime = System.currentTimeMillis();

                HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

                String requestID = UUID.randomUUID().toString();

                httpResponse.setHeader("X-Request-ID", requestID);

                // Reqquest Log
                System.out.println("Incoming Request: " 
                    + httpRequest.getMethod() + " " 
                    + httpRequest.getRequestURI());


                // filterChain.doFilter(servletRequest, servletResponse);
                try {
                    filterChain.doFilter(servletRequest, servletResponse);
                } finally {
                    long duration = System.currentTimeMillis() - startTime;

                    // Response Status Log
                    System.out.println("Outgoing Response: " 
                        + httpResponse.getStatus());

                    System.out.println("API Response time: " + duration);
                }

                    
    }

}
