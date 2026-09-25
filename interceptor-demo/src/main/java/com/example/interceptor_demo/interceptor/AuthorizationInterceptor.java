package com.example.interceptor_demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String userRole = request.getHeader("x-user-role");

        response.setContentType("application/json");
        if(userRole != null && !userRole.equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\r\n" + //
                                "    \"meaasage\": \"You are not authorized to perform this action\"\r\n" + //
                                "}");
            return false;
        } else {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
    
}
