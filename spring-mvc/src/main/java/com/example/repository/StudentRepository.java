package com.example.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.entity.Student;

@Repository 
public class StudentRepository {

    private Map<Long, Student> studentDB;

    public StudentRepository() {
        studentDB = new HashMap<>();
    }

    public Student save(Student studenReq) {
        studentDB.put(studenReq.getId(), studenReq);
        return studenReq;
    }

    public Student findById(Long id) {
        return studentDB.get(id);
    } 

    public List<Student> findAll() {
        return new ArrayList<>(studentDB.values());
    }
}
