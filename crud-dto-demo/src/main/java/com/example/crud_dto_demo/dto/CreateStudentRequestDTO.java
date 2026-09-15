package com.example.crud_dto_demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateStudentRequestDTO {

    @NotBlank(message = "Name Required")
    private String name;

    @NotBlank (message = "Email cannot be blank")
    @Email (message = "Email must valid")
    private String email;

    @NotNull(message = "Age Required")
    @Min(value = 18, message = "Student must be atleast 18 years old")
    private Integer age;

    @NotNull(message = "roll no Required")
    @Min(value = 1)
    private Integer rollNo;

    @NotBlank (message = "Subject cannot be blank")
    private String subject;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Integer getRollNo() {
        return rollNo;
    }
    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

}
