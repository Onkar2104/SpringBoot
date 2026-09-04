package com.example;

import org.springframework.stereotype.Component;

// Circular dependancy 
// @Component
// public class PaymentService {

//     @Autowired
//     OrderService orderService;

//     // public PaymentService(OrderService orderService) {
//     //     this.orderService = orderService;
//     // }

//     public void pay() {
//         System.out.println("Payment Done");

//         orderService.getOrderDetails();
//     }
// }


// Inline dependency
@Component
public class PaymentService {

    public void pay() {
        System.out.println("Payment Done");
    }
}