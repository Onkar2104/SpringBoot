package com.example.filter_demo.filter;

import java.io.IOException;

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
@Order(1)  
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

                HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

                String token = httpRequest.getHeader("token");
                String apiKey = httpRequest.getHeader("x-api-key");

                if(token == null || !token.equals("12345")) {
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                if(apiKey == null || !apiKey.equals("secret")) {
                    httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    httpResponse.setContentType("application/json");
                    httpResponse.getWriter().write(
                        "{\r\n" + //
                                                        "    \"message\": \"Invalid or missing api key\"\r\n" + //
                                                        "}"
                    );

                    return;
                }

                filterChain.doFilter(servletRequest, servletResponse);
    }

}
