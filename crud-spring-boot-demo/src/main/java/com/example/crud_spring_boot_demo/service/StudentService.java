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
        studentReq.setDeleted(false); 
        Student studentResp = studentRepository.save(studentReq);
        return studentResp;
    }

    public Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);

        if(studentResp.isPresent()) {
            return studentResp.get();
        } else {
            return null;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> studentResp = studentRepository.findByDeletedIsFalse();

        return studentResp;
    }

    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNo((studentReq.getRollNo()));
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setSubject(studentReq.getSubject());

        // studentToSave.setDeleted(studentReq.getDeleted());
        studentToSave.setDeleted(false);

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

    public Boolean deleteStudentSoftly(Long id) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) {
            return false;
        }
        
        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(true);
        studentRepository.save(studentToSave);

        return true;
    }

    public Boolean recoverAcc(Long id) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsTrue(id);

        if(existingStudent.isEmpty()) {
            return false;
        }
        
        Student studentToSave = existingStudent.get();
        studentToSave.setDeleted(false);
        studentRepository.save(studentToSave);

        return true;
    }
}
