package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.example.demo.HelloController;
import com.example.payment.CardPayment;
import com.example.payment.PaymentService;

@Configuration
@ComponentScan("com.example")
public class AppConfig {
    @Bean
    public User createUser() {
        return new User("Aditya", 28);
    }

    @Bean
    public HelloController createExtraController() {
        return new HelloController();
    }

    @Bean
    public PaymentService createCardPaymentService() {
        return new CardPayment();
    }

    @Bean
    @Primary
    public OrderService creaOrderService(PaymentService paymentService) {
        return new OrderService(paymentService);
    }
}
