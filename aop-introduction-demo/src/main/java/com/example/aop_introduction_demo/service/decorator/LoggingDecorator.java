package com.example.aop_introduction_demo.service.decorator;

import org.springframework.stereotype.Component;

import com.example.aop_introduction_demo.dto.Student;
import com.example.aop_introduction_demo.service.StudentService;
import com.example.aop_introduction_demo.service.implementation.StudentServiceImpl;

@Component 
public class LoggingDecorator implements StudentService {

    private StudentServiceImpl studentServiceImpl;

    public LoggingDecorator(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
    }

    
    @Override
    public void createStudent(Student student) {

        // Logging related login
        LoggingServiceUtil.logStart("StudentServiceImpl", "createStudent");
        
        studentServiceImpl.createStudent(student);

        LoggingServiceUtil.logEnd("StudentServiceImpl", "createStudent");
    }
}
