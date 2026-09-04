package in.strikes;

// import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
// @Lazy
public class PaymentGateway {
    public PaymentGateway(PaymentService paymentService) {
        System.out.println("Payment gateway created");
        
    } 
}
