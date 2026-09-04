package com.example.crud_spring_boot_demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.crud_spring_boot_demo.entity.Student;
import com.example.crud_spring_boot_demo.service.StudentService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity
                // .status(201)
                // OR 
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student studentResp = studentService.getStudent(id);

        if(studentResp == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
        }   return ResponseEntity
                .status(200)
                .body(studentResp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentService.getAllStudents();

        if(studentList.isEmpty()) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
        }   return ResponseEntity
                .status(200)
                .body(studentList);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentReq) {
        Student studentResp = studentService.updateStudent(id, studentReq);

        if(studentResp == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
        }   return ResponseEntity
                .status(200)
                .body(studentResp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Boolean isDeleted = studentService.deleteStudent(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }   return ResponseEntity.ok("Record Deleted");
    }
     
}
