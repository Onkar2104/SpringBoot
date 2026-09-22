package com.example.filter_demo.service;

import org.springframework.stereotype.Service;

import com.example.filter_demo.dto.Student;

@Service 
public class StudentService {

    public void createStudent(Student student) {
        System.out.println("Student created");
        System.out.println(student.getName());
        System.out.println(student.getEmail());

        // try {
        //     Thread.sleep(2000);
        // } catch(Exception e) {}
    }
}
