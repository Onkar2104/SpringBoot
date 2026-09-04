package com.example;

// import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
// @Scope("prototype")
public class PaymentService {

    public PaymentService() {
        System.out.println("Constructor called");
    }

    @PostConstruct
    public void init() {
        System.out.println("Post Constructur called");
    }

    @PreDestroy
    public void stop() {
        System.out.println("Pre Destroy called");
    }

    public void pay() {
        System.out.println("Payment Done");
    }
}
