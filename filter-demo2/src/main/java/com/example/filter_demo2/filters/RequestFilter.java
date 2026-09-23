package com.example.filter_demo2.filters;

import java.io.BufferedReader;
import java.io.IOException;

// import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component 
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        
                HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
                // HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

                BufferedReader reader = httpServletRequest.getReader();

                StringBuilder body = new StringBuilder();

                String line = reader.readLine();

                while(line != null) {
                    body.append(line);
                    line = reader.readLine();
                }

                System.out.println(body);

                filterChain.doFilter(servletRequest, servletResponse);
    }
}
