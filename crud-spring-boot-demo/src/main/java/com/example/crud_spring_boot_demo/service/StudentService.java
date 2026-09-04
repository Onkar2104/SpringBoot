package com.example.crud_spring_boot_demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.crud_spring_boot_demo.entity.Student;
import com.example.crud_spring_boot_demo.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        Student studentResp = studentRepository.save(studentReq);
        return studentResp;
    }

    public Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findById(id);

        if(studentResp.isPresent()) {
            return studentResp.get();
        } else {
            return null;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> studentResp = studentRepository.findAll();

        return studentResp;
    }

    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNo((studentReq.getRollNo()));
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setSubject(studentReq.getSubject());
        return studentRepository.save(studentToSave);
    }

    public Boolean deleteStudent(Long id) {
        Boolean isStudent = studentRepository.existsById(id);
 
        if(!isStudent) {
            return false;
        }
        
        studentRepository.deleteById(id);
        return true;
    }
}
