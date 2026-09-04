package com.example.spring_boot_core_demo;

import org.springframework.stereotype.Component;

@Component
public class PaymentGateway {

    private PaymentProperties paymentProperties;

    public PaymentGateway(PaymentProperties paymentProperties) {
        this.paymentProperties = paymentProperties;
    }

    public String getType() {
        return paymentProperties.getType();
    }

    public int getRetryCount() {
        return paymentProperties.getRetryCount();
    }

    public boolean isEnabled() {
        return paymentProperties.isEnabled();
    }

    public int getTimeOut() {
        return paymentProperties.getTimeOut();
    }

    public void print() {
        System.out.println(getType());
		System.out.println(getRetryCount());
		System.out.println(isEnabled());
		System.out.println(getTimeOut());
    }
}

// Value

    // @Value("${paymentGateway.type:Razorpay}")
    // private String type;
    // @Value("${paymentGateway.retryCount:8}")
    // private int retryCount;

    // public PaymentGateway(@Value("${paymentGateway.type}") String type, @Value("${paymentGateway.retryCount}") int retryCount) {
    //     this.type = type;
    //     this.retryCount = retryCount;
    // }
