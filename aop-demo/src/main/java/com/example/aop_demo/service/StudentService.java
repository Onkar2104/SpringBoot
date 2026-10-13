package com.example.aop_demo.service;

import org.springframework.stereotype.Service;
import com.example.aop_demo.dto.StudentDTO;

// import jdk.jfr.Timestamp;

import com.example.aop_demo.annotation.TrackExecutionTime;

@Service 
public class StudentService {

    @TrackExecutionTime (
        warnAfter = 2000,
        operation = "Post student data"
    )
    public StudentDTO createStudent(StudentDTO studentDto) {
        System.out.println("Student Saved");
        // throw new RuntimeException("Some new error");

        return studentDto;
    }

    // @Timestamp 
    @TrackExecutionTime (
        warnAfter = 1500, 
        operation = "Get Student data"
    )
    public String getStudent(String s) {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }
        System.out.println(s);
        return s;
    }

    public int dummyMethod() {
        return 1;
    }
}
 