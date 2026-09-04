package com.example.spring_boot_core_demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DemoRunner implements ApplicationRunner {

    private OrderService orderService;

    public DemoRunner(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        orderService.placeOrder();
    }

}
