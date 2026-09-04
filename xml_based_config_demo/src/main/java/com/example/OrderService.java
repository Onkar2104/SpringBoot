package com.example;

public class OrderService {

    private PaymentService paymentService;

    // public OrderService(PaymentService paymentService) {
    //     this.paymentService = paymentService;
    //     // System.out.println("Order Service created");
    // }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.pay();
        System.out.println("Order Placed");
    }
}
