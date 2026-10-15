package com.example.hibernate_demo.service;

import com.example.hibernate_demo.model.Student;
import com.example.hibernate_demo.repository.StudentRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional 
    public void createStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return null;
    }

    @Transactional 
    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional 
    public void updateStudent(Student studentReq, Long id) {
        Student student2 = studentRepository.findById(id);

        if(student2 == null) {
            throw new RuntimeException("Student not found");
        }

        student2.setName(studentReq.getName());
        student2.setEmail(studentReq.getEmail());
        student2.setAge(studentReq.getAge());
    }

    @Transactional 
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id);

        if(student == null) {
            throw new RuntimeException("Student not found");
        }

        studentRepository.remove(student);
    }
}
