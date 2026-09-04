package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// import com.example.demo.HelloController;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        OrderService order = context.getBean(OrderService.class);
        order.placeOrder();

        // call using general object creation
        // HelloController bills = new HelloController();
        // bills.Bill();

        // call using beans (AppConfig)
        // HelloController billsContext = context.getBean(HelloController.class);
        // billsContext.Bill();

        User user = context.getBean(User.class);
        System.out.println(user.getName());

    }
}