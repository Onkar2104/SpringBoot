package com.example.payment;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Qualifier   - wee need to decide which one to qualify 
@Primary
public class UPIPayment implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Pay using UPI");
    }
}
