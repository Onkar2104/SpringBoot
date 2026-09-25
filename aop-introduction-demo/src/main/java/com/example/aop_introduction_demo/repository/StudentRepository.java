package com.example.aop_introduction_demo.repository;

import org.springframework.stereotype.Repository;

import com.example.aop_introduction_demo.dto.Student;
 
@Repository 
public class StudentRepository {

    public void save(Student student) {
        System.out.println("Student Saved");
    }
}
