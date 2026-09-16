package com.example.crud_dto_demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud_dto_demo.dto.CreateStudentRequestDTO;
import com.example.crud_dto_demo.dto.CreateStudentResponseDTO;
import com.example.crud_dto_demo.dto.UpdateStudentRequestDTO;
import com.example.crud_dto_demo.dto.UpdateStudentResponseDTO;
import com.example.crud_dto_demo.entity.Student;
import com.example.crud_dto_demo.exception.DuplicateResourceException;
import com.example.crud_dto_demo.exception.ResourceNotFoundException;
import com.example.crud_dto_demo.repository.StudentRepository;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentReqRequestDTO) {
        Student student = mapToEntity(studentReqRequestDTO);

        if(emailExists(student)) {
            throw new DuplicateResourceException("Student with " + student.getEmail() + " already exists.");
        }

        Student studentResp = studentRepository.save(student);

        return mapToDTO(studentResp);
    }

    public CreateStudentResponseDTO getStudent(Long id) {
        Student studentResp = studentRepository
                                    .findById(id)
                                    .orElseThrow( () -> 
                                            new ResourceNotFoundException("Student with id " + id + " not found"));

        return mapToDTO(studentResp);
    }

    public List<CreateStudentResponseDTO> getAllStudents() {
        List<Student> studentResp = studentRepository.findByDeletedIsFalse();

        // mapToDTO(null)

        return studentResp.stream() 
            .map(this::mapToDTO)
            .toList();
    }

    public UpdateStudentResponseDTO updateStudent(Long id, UpdateStudentRequestDTO studentReq) {
        Student existingStudent = studentRepository
                                    .findByIdAndDeletedIsFalse(id)
                                    .orElseThrow( () ->
                                                    new ResourceNotFoundException(("Student with id " + id + " not found")));

        existingStudent.setName(studentReq.getName());
        existingStudent.setRollNo((studentReq.getRollNo()));
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setUpdatedAt(LocalDateTime.now());

        existingStudent.setDeleted(false);

        Student savedStudent = studentRepository.save(existingStudent);

        return mapToUpdateDTO(savedStudent);
    }

    public void deleteStudent(Long id) {
        Student studentToBeDeleted = studentRepository
                                        .findById(id)
                                        .orElseThrow( () ->
                                            new ResourceNotFoundException("Student with id " + id + " not found"));
        
        studentRepository.delete(studentToBeDeleted);
    }

    public void deleteStudentSoftly(Long id) {
        Student studentToBeDeleted = studentRepository
                                        .findById(id)
                                        .orElseThrow( () ->
                                            new ResourceNotFoundException("Student with id " + id + " not found"));

        
        studentToBeDeleted.setDeleted(true);
        studentRepository.save(studentToBeDeleted);
    }

    public void recoverAcc(Long id) {
        Student recoverStudent = studentRepository
                                    .findByIdAndDeletedIsTrue(id)
                                    .orElseThrow( () ->
                                        new ResourceNotFoundException("Student with id " + id + " not found"));
        
        recoverStudent.setDeleted(false);
        studentRepository.save(recoverStudent);
    }

    private Student mapToEntity(CreateStudentRequestDTO studentRequestDTO) {
        Student student = new Student();

        student.setName(studentRequestDTO.getName());
        student.setAge((studentRequestDTO.getAge()));
        student.setEmail(studentRequestDTO.getEmail());
        student.setRollNo(studentRequestDTO.getRollNo());
        student.setSubject(studentRequestDTO.getSubject());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

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

    private boolean emailExists(Student student) {
        return studentRepository.existsByEmail(student.getEmail());
    }
}
