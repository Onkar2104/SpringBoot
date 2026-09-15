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

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDTO> createStudent(@Valid @RequestBody CreateStudentRequestDTO studentRequestDTO) {
        CreateStudentResponseDTO createdStudent = studentService.createStudent(studentRequestDTO);
        return ResponseEntity
                // .status(201)
                // OR 
                .status(HttpStatus.CREATED)
                .body(createdStudent);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CreateStudentResponseDTO> getStudent(@PathVariable Long id) {
        CreateStudentResponseDTO studentResp = studentService.getStudent(id);

        if(studentResp == null) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(null);
        }   return ResponseEntity
                .status(200)
                .body(studentResp);
    }

    @GetMapping("/getAll")
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

    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponseDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody UpdateStudentRequestDTO studentReq) {
        UpdateStudentResponseDTO studentResp = studentService.updateStudent(id, studentReq);

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

    @PatchMapping("/delete-soft/{id}")
    public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id) {
        Boolean isDeleted = studentService.deleteStudentSoftly(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record Deleted");
    }

    @PatchMapping("/recover-acc/{id}")
    public ResponseEntity<String> recoverAcc(@PathVariable Long id) {
        Boolean isDeleted = studentService.recoverAcc(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Account Recovered");
    }
     
}