package com.example.interceptor_demo.interceptor;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("Incoming Request --------------");
        System.out.println("HTTP Method: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request Parameters: " + request.getQueryString());
        System.out.println("Client IP: " + request.getRemoteAddr());
        System.out.println("Token Header: " + request.getHeader("token"));

        if(handler instanceof HandlerMethod handlerMethod) {
            System.out.println("Controller: " + handlerMethod.getBeanType().getName());
            System.out.println("Controller Method: " + handlerMethod.getMethod().getName());
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {

        System.out.println("Response Status: " + response.getStatus());

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
    
}
