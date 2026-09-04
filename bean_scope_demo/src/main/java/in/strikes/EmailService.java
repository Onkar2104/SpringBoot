package in.strikes;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class EmailService {
    public EmailService() {
        System.out.println("EmailService Created");
    }

    public void sendEmail() {
        System.out.println("Email Send");
    }
}
