package com.example.crud_spring_boot_demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crud_spring_boot_demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIdAndDeletedIsFalse(Long id);
    List<Student> findByDeletedIsFalse();
    Optional<Student> findByIdAndDeletedIsTrue(Long id);
}
