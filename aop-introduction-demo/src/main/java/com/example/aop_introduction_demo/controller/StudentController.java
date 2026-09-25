package com.example.aop_introduction_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aop_introduction_demo.dto.Student;
import com.example.aop_introduction_demo.service.StudentService;

@RestController 
@RequestMapping("/api/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping 
    public ResponseEntity<String> createStudent(Student student) {
        studentService.createStudent(student);
        return ResponseEntity.ok("Done");
    }
}
