package com.example.crud_spring_boot_demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.crud_spring_boot_demo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    
}
