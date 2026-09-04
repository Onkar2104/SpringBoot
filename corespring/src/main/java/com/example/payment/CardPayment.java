package com.example.payment;

import org.springframework.stereotype.Component;

@Component
// @Qualifier   - We need to decide which one should qualify 
public class CardPayment implements PaymentService {

    // @Override
    public void pay() {
        System.out.println("Pay using card");
    }
}
