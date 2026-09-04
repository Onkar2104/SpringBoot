package com.example.spring_boot_core_demo;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {

    private PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public void pay() {
        System.out.println("Payment Done using: " + paymentGateway.getType());
        paymentGateway.print();
    }
}