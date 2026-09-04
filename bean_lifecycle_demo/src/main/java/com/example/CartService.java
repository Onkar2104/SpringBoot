package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
// import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
// import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
// Initialization Callbackk
// public class CartService implements InitializingBean {

// Destruction Callback
// @Component
// public class CartService implements BeanNameAware, ApplicationContextAware, DisposableBean {

public class CartService implements BeanNameAware, ApplicationContextAware {

    Map<Integer, String> mp;

    public CartService() {
        mp = new HashMap<>();
        System.out.println("CartService constructor called");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean Name is: " + name);        
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContext name is: " + applicationContext.getClass());        
    }

// Initialization callbacks 
// 1. Initializing Bean
    // @Override
    // public void afterPropertiesSet() throws Exception {
    //     System.out.println("Initialization Callback");
    //     mp.put(1, "Aditya");
    //     mp.put(2, "Rohit");        
    // }

// 2. init Method
    // public void start() {
    //     System.out.println("Initialization Callback");
    //     mp.put(1, "Aditya");
    //     mp.put(2, "Rohit");    
    // }

// 3. post Construct
    @PostConstruct
    public void start2() {
        System.out.println("Initialization Callback");
        mp.put(1, "Aditya");
        mp.put(2, "Rohit");        
    }

    // 

    public void addToCart() {
        System.out.println("Added To cart");
    }

    public String getValue(int key) {
        return mp.get(key);
    }

// Destruction Callback
// 1. Disposable Bean
    // @Override
    // public void destroy() throws Exception {
    //     mp.clear();;
    //     System.out.println("Bean is getting destroyed");        
    // }

// 2. destroyMethod
    // public void stop() {
    //     mp.clear();
    //     System.out.println("Bean is getting destroyed by destroy method");
    // }

// 3. Pre Destroy
    @PreDestroy
    public void stop2() {
        mp.clear();
        System.out.println("Bean is getting destroyed by pre destroy");
    }
}
