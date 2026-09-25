package com.example.aop_introduction_demo.service.decorator;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.example.aop_introduction_demo.dto.Student;
import com.example.aop_introduction_demo.service.StudentService;

@Component 
@Primary 
public class ExecutionTimeDecorator implements StudentService {

    private LoggingDecorator loggingDecorator;

    public ExecutionTimeDecorator(LoggingDecorator loggingDecorator) {
        this.loggingDecorator = loggingDecorator;
    }
    

    @Override
    public void createStudent(Student student) {
        long startTime = System.currentTimeMillis();

        loggingDecorator.createStudent(student);

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

}
