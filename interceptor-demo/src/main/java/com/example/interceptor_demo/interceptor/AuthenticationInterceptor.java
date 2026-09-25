package com.example.interceptor_demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String apikey = request.getHeader("x-api-key");

        if(apikey != null && !apikey.equals("12345")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\r\n" + //
                                "    \"meaasage\": \"Please login first\"\r\n" + //
                                "}");
            return false;
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    
}
