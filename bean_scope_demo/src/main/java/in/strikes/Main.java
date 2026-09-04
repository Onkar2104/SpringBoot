package in.strikes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("Application Started");

        // UserService user = context.getBean(UserService.class);
        // UserService user1 = context.getBean(UserService.class);
        // System.out.println(user == user1);

        // UserService userService = context.getBean(UserService.class);

        // System.out.println("Before registerUser()");

        // userService.registerUser();;

        // PaymentGateway paymentGateway = context.getBean(PaymentGateway.class);
        // paymentGateway.PaymentPage();;
    }
}
