package com.example.crud_dto_demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.crud_dto_demo.dto.CreateStudentRequestDTO;
import com.example.crud_dto_demo.dto.CreateStudentResponseDTO;
import com.example.crud_dto_demo.dto.UpdateStudentRequestDTO;
import com.example.crud_dto_demo.dto.UpdateStudentResponseDTO;
import com.example.crud_dto_demo.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@Valid @RequestBody CreateStudentRequestDTO studentRequestDTO) {
        CreateStudentResponseDTO createdStudent = studentService.createStudent(studentRequestDTO);
        return ResponseEntity
                // .status(201)
                // OR 
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@PathVariable Long id) {
        CreateStudentResponseDTO studentResp = studentService.getStudent(id);

        return ResponseEntity.ok(studentResp);
    }

    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDTO>> getAllStudent() {
        List<CreateStudentResponseDTO> studentList = studentService.getAllStudents();

        if(studentList.isEmpty()) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
        }   return ResponseEntity
                .status(200)
                .body(studentList);
    }

    @PutMapping
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@RequestParam Long id, @Valid @RequestBody UpdateStudentRequestDTO studentReq) {
        UpdateStudentResponseDTO studentResp = studentService.updateStudent(id, studentReq);

        return ResponseEntity.ok(studentResp);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        studentService.deleteStudent(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/delete-soft")
    public ResponseEntity<String> deleteStudentSoftly(@RequestParam Long id) {
        studentService.deleteStudentSoftly(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();    
    }

    @PatchMapping("/recover-acc")
    public ResponseEntity<String> recoverAcc(@RequestParam Long id) {
        studentService.recoverAcc(id);

        return ResponseEntity.ok("Account Recovered");
    }
     
}