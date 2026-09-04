package com.example.spring_boot_core_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootCoreDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCoreDemoApplication.class, args);

		// PaymentGateway paymentGateway = context.getBean(PaymentGateway.class);
		// paymentGateway.print();

	}

}
