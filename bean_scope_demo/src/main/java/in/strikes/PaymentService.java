package in.strikes;

import org.springframework.stereotype.Component;

@Component
public class PaymentService {
    public PaymentService(PaymentGateway paymentGateway) {
        System.out.println("PaymentService Created");
    }
}
