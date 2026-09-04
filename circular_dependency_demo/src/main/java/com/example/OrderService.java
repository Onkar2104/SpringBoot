package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Circular Dependency
// @Component
// public class OrderService {

//     @Autowired
//     private PaymentService paymentService;

//     // public OrderService(PaymentService paymentService) {
//     //     this.paymentService = paymentService;
//     // }

//     public void placeOrder() {
//         paymentService.pay();

//         System.out.println("Order Placed");
//     }

//     public void getOrderDetails() {
//         System.out.println("Order Details");
//     }
// }



// Inline Dependency
@Component
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    public void placeOrder() {
        paymentService.pay();

        getOrderDetails();

        System.out.println("Order Placed");
    }

    public void getOrderDetails() {
        System.out.println("Order Details");
    }
}
