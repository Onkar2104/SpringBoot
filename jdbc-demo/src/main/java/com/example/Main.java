package com.example;

import com.example.model.Student;
import com.example.repository.StudentRepository;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        StudentRepository studentRepository = new StudentRepository();

        // studentRepository.createStudent(new Student(
        //     "Rohan",
        //     "rohan@gmail.com",
        //     26
        // ));
        // studentRepository.updateStudent(new Student("Rohit Sharma", "rohit@gmail.com", 26), 3L);
        // studentRepository.deleteStudent(5L);
        // studentRepository.getStudentById(3L);
        studentRepository.getStudents();
    }
}

// 