package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        
        // get bean by id/name
        // OrderService orderService = (OrderService) context.getBean("orderService");  

        // get bean by type 
        // OrderService orderService = context.getBean(OrderService.class);

        // get bean by both (id/name and type)
        // OrderService orderService = context.getBean("orderService", OrderService.class);
        // orderService.placeOrder();

        // PaymentService paymentService = context.getBean("paymentService", PaymentService.class);
        // paymentService.pay();

        UserService user = context.getBean(UserService.class);
        System.out.println(user.getUsernames());
    }
}