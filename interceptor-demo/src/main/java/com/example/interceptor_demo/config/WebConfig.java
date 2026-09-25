package com.example.interceptor_demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.interceptor_demo.interceptor.AuthenticationInterceptor;
import com.example.interceptor_demo.interceptor.AuthorizationInterceptor;
import com.example.interceptor_demo.interceptor.LoggingInterceptor;

@Configuration 
public class WebConfig implements WebMvcConfigurer {

    public LoggingInterceptor loggingInterceptor;
    public AuthenticationInterceptor authenticationInterceptor;
    public AuthorizationInterceptor authorizationInterceptor;

    public WebConfig(LoggingInterceptor loggingInterceptor, AuthenticationInterceptor authenticationInterceptor, AuthorizationInterceptor authorizationInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
        this.authenticationInterceptor = authenticationInterceptor;
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/*")
                .excludePathPatterns("/api/auth/login", "/api/public/**")
                .order(1);

        registry.addInterceptor(loggingInterceptor)
                .order(3);
        registry.addInterceptor(authorizationInterceptor)
                .order(2);
        // WebMvcConfigurer.super.addInterceptors(registry);
    }

    
}
