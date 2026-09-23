package com.example.filter_demo2.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.filter_demo2.filters.DummyFilter;

@Configuration 
public class FilterConfig {

    @Bean 
    public FilterRegistrationBean<DummyFilter> getDummyFilterBean() {
        FilterRegistrationBean<DummyFilter> registraationBean = new FilterRegistrationBean<>();

        registraationBean.setFilter(new DummyFilter());

        // registraationBean.setOrder(0);
        registraationBean.addUrlPatterns("/api/*");
        
        return registraationBean;
    }
}
