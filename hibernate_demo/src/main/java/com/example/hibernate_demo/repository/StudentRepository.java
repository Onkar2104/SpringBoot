package com.example.hibernate_demo.repository;

import org.springframework.stereotype.Repository;

import com.example.hibernate_demo.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository 
public class StudentRepository {

    @PersistenceContext 
    private EntityManager entityManager;

    // create
    public void save(Student student) {
        entityManager.persist(student);
    }

    // update
    // public void update(Student student) {
    //     entityManager.persist(student);
    // }

    // read
    public Student findById(Long id) {
        return entityManager.find(Student.class, id); 
    }

    // delete 
    public void remove(Student student) {
        entityManager.remove(student);
    }
}
