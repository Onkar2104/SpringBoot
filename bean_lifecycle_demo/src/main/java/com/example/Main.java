package com.example;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // OrderService order = context.getBean(OrderService.class);
        // order.placeOrder();;

        CartService cartService = context.getBean(CartService.class);
        System.out.println(cartService.getValue(1));

        // PaymentService paymentService = context.getBean(PaymentService.class);
        

        context.close();
    }
}