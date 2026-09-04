// package in.strikes;

// import org.springframework.context.annotation.Lazy;
// import org.springframework.context.annotation.Scope;
// import org.springframework.stereotype.Component;

// @Component
// @Scope("prototype")
// public class UserService {
//     private final EmailService emailService;

//     public UserService(@Lazy EmailService emailService) {
//         this.emailService = emailService;

//         System.out.println("UserService Created");
//     }

//     public void registerUser() {
//         System.out.println("User registered");
//         emailService.sendEmail();
//     }

//     // public UserService() {
//     //     System.out.println("UserService Created");
//     // }
// }
