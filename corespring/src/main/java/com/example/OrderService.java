package com.example;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.payment.PaymentService;

@Component
public class OrderService {

    private PaymentService paymentService;

    // @Autowired
    public OrderService(PaymentService paymentService) {      // @Qualifier("cardPayment") PaymentService paymentService) - cardPayment bean qualified
        this.paymentService = paymentService;
    }

    // @Autowired
    // public void setPaymentService(PaymentService paymentService) {
    //     this.paymentService = paymentService;
    // }

    public void placeOrder() {

        paymentService.pay();

        System.out.println("Order Placed");
    }
}
