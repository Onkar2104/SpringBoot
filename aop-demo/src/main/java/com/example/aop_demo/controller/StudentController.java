package com.example.aop_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aop_demo.service.StudentService;
import com.example.aop_demo.dto.StudentDTO;

@RestController 
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping 
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDto) {
        return ResponseEntity.ok(studentService.createStudent(studentDto));
    }

    @GetMapping 
    public ResponseEntity<String> getStudent() {
        String s = "All student data";
        return ResponseEntity.ok(studentService.getStudent(s));
    }
}
