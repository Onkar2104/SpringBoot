package com.example.crud_dto_demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.crud_dto_demo.dto.CreateStudentRequestDTO;
import com.example.crud_dto_demo.dto.CreateStudentResponseDTO;
import com.example.crud_dto_demo.dto.UpdateStudentRequestDTO;
import com.example.crud_dto_demo.dto.UpdateStudentResponseDTO;
import com.example.crud_dto_demo.entity.Student;
import com.example.crud_dto_demo.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentReqRequestDTO) {
        Student student = mapToEntity(studentReqRequestDTO);

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        Student studentResp = studentRepository.save(student);

        return mapToDTO(studentResp);
    }

    public CreateStudentResponseDTO getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);

        if(studentResp.isPresent()) {
            return mapToDTO(studentResp.get());
        } else {
            return null;
        }
    }

    public List<CreateStudentResponseDTO> getAllStudents() {
        List<Student> studentResp = studentRepository.findByDeletedIsFalse();

        // mapToDTO(null)

        return studentResp.stream()
            .map(this::mapToDTO)
            .toList();
    }

    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNo((studentReq.getRollNo()));
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setUpdatedAt(LocalDateTime.now());

        studentToSave.setDeleted(false);

        Student savedStudent = studentRepository.save(studentToSave);

        return mapToUpdateDTO(savedStudent);
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

    private Student mapToEntity(CreateStudentRequestDTO studentRequestDTO) {
        Student student = new Student();

        student.setName(studentRequestDTO.getName());
        student.setAge((studentRequestDTO.getAge()));
        student.setEmail(studentRequestDTO.getEmail());
        student.setRollNo(studentRequestDTO.getRollNo());
        student.setSubject(studentRequestDTO.getSubject());

        student.setDeleted(false);

        return student;
    }

    private CreateStudentResponseDTO mapToDTO(Student student) {
        CreateStudentResponseDTO responseDTO = new CreateStudentResponseDTO();
        
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge((student.getAge()));
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setMessage("Student Saved Successfully");
        responseDTO.setCreatedAt(student.getCreatedAt());
        responseDTO.setUpdatedAt(student.getUpdatedAt());

        return responseDTO;
    }

    private UpdateStudentResponseDTO mapToUpdateDTO(Student student) {

        UpdateStudentResponseDTO responseDTO = new UpdateStudentResponseDTO();
        
        responseDTO.setId(student.getId());
        responseDTO.setName(student.getName());
        responseDTO.setAge((student.getAge()));
        responseDTO.setEmail(student.getEmail());
        responseDTO.setRollNo(student.getRollNo());
        responseDTO.setSubject(student.getSubject());
        responseDTO.setMessage("Student updated Successfully");
        responseDTO.setUpdatedAt(student.getUpdatedAt());

        return responseDTO;
    }
}
