package com.example;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
// @Scope("singleton")     // By default, creates only one object for all getBeans call. (Eager Initialization)
                          // IOC container creates only one object per bean.
@Scope("prototype")     // Creates different opbjects for all getBeans call. (Lazy Initialization)
public class OrderService {

    public OrderService() {
        System.out.println("Order service created");
    }

    public void placeOrder() {
        System.out.println("Order Placed");
    }
}
 