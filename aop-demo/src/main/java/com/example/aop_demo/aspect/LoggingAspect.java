// package com.example.aop_demo.aspect;

// // import org.aspectj.lang.annotation.After;
// // import org.aspectj.lang.annotation.AfterReturning;
// // import org.aspectj.lang.annotation.AfterThrowing;
// // import org.aspectj.lang.annotation.Aspect;
// // import org.aspectj.lang.annotation.Before;
// // import org.springframework.stereotype.Component;

// // import com.example.aop_demo.dto.StudentDTO;

// // @Component 
// // @Aspect 
// // public class LoggingAspect {

// //     // @Before("execution(String com.example.aop_demo.service.StudentService.createStudent())")
// //     // public void logBeforeMethod() {
// //     //     System.out.println("Info of student is going to be saved");
// //     // }

// //     // @AfterReturning(
// //     //     value = "execution(" + 
// //     //         "* com.example.aop_demo.service.StudentService" + 
// //     //         ".createStudent(..))",
// //     //     returning = "result"
// //     // )
// //     // public void logAfterReturningMethod(StudentDTO result) {

// //     //     result.setName("Rohit");
// //     //     result.setAge(21);
// //     //     System.out.println("Intercepted createStudent()");

// //     //     System.out.println("After returning method");
// //     // }

// //     // @After("execution(String com.example.aop_demo.service.StudentService.createStudent())")
// //     // public void logAfterMethod() {
// //     //     System.out.println("After method");
// //     // }

// //     @AfterThrowing("execution(* com.example.aop_demo.service.StudentService.createStudent(..))")
// //     public void logAfterThrowing() {
// //         System.out.println("An exception occured");
// //         System.out.println("Log after throwing method");
// //     }
// // }

// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
// import org.aspectj.lang.annotation.Pointcut;
// import org.springframework.stereotype.Component;

// @Component 
// @Aspect 
// public class LoggingAspect {

//     // to store pointcut 
//     @Pointcut ("execution(public * com.example.aop_demo.service.StudentService..*(..))")
//     public void logPublicServiceMethod() {

//     }
    
//     // @Before ("execution(public * com.example.aop_demo.service.StudentService.*(..))")
//     // @Before ("execution(* com.example.aop_demo..*(..))")
//     // @Before ("within(com.example.aop_demo.service.*)")
//     // @Before ("@annotation(jdk.jfr.Timestamp)")
//     // @Before ("bean(studentService) || bean(studentController)")
//     // @Before ("logPublicServiceMethod()")
//     @Before ("com.example.aop_demo.aspect.ApplicationPointcuts.controllerLayer()")
//     public void logBeforeMethod() {
//         System.out.println("Method Intercepted");
//     }

//     // @Before ("execution(public com.example.aop_demo.dto.StudentDTO " +
//     //          "com.example.aop_demo.service.StudentService.createStudent(" + 
//     //          "com.example.aop_demo.dto.StudentDTO))") 
//     // public void logBeforeMethod2() {
//     //     System.out.println("Method Intercepted");
//     // }
// }



package com.example.aop_demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.example.aop_demo.annotation.TrackExecutionTime;

@Component 
@Aspect 
public class LoggingAspect {

    @Before ("@annotation(jdk.jfr.Timestamp)")
    public void logBeforeMethod() {
        System.out.println("Method Intercepted");
    }


    // @Around("@annotation(com.example.aop_demo.annotation.TrackExecutionTime)")
    // public Object trackExecutionTime(ProceedingJoinPoint joinPoint)
    //         throws Throwable {

    @Around("@annotation(trackExecutionTime)")
    public Object trackExecutionTime(ProceedingJoinPoint joinPoint,
                                     TrackExecutionTime trackExecutionTime ) 
            throws Throwable {
                
        long startTime = System.currentTimeMillis();
        
        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            String operation = trackExecutionTime.operation(); 
            if(operation.isBlank()) {
                operation = joinPoint.getSignature().getName();
            }

            long warningThreshold = trackExecutionTime.warnAfter();
            if(duration >= warningThreshold) {
                System.out.println("Slow operation alert: " +
                    "Time taken by " + operation + " : " + duration
                );
            } else {
                System.out.println("Time taken by " + operation + " : " + duration);
            }
        }
    }
}