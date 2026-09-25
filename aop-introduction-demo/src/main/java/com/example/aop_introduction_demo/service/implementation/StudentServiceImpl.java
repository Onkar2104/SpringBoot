package com.example.aop_introduction_demo.service.implementation;

import org.springframework.stereotype.Service;

import com.example.aop_introduction_demo.dto.Student;
import com.example.aop_introduction_demo.repository.StudentRepository;
import com.example.aop_introduction_demo.service.StudentService;

@Service 
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        studentRepository.save(student);
    }
}
