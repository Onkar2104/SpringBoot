package com.example.filter_demo2.service;

import org.springframework.stereotype.Service;

import com.example.filter_demo2.dto.Student;
import com.example.filter_demo2.dto.StudentResponseDTO;

@Service 
public class StudentService {

    public StudentResponseDTO createStudent(Student student) {
        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setName(student.getName());
        responseDTO.setMessage("Student saved successfully");

        return responseDTO;
    }
}