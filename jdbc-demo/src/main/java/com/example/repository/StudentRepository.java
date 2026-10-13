package com.example.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Student;

public class StudentRepository {

    String url = "jdbc:mysql://localhost:3306/student_crud";
    String username = "root";
    String pass = "mysql";

    // public void createStudent(Student student) {
    //     try {
    //         Connection connection = DriverManager.getConnection(url, username, pass);

    //         // Statement statement = connection.createStatement();
            
    //         // String sql = "INSERT INTO jdbc_student (name, email, age)" + 
    //         //              "VALUES ('Aditya', 'adi@gmail.com', 28)";

    //         // String sql = """
    //         //         INSERT INTO jdbc_student(name, email, age)
    //         //         VALUES('%s', '%s', '%d')
    //         //         """.formatted(
    //         //                 student.getName(),
    //         //                 student.getEmail(),
    //         //                 student.getAge()
    //         //         );

    //         int result = statement.executeUpdate(sql);

    //         if(result == 1) {
    //             System.out.println("Create operation successfull.");
    //         } else {
    //             System.out.println("Creation failed");
    //         }

    //         connection.close();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }


    public void createStudent(Student student) {

        String sql = """
                    INSERT INTO jdbc_student(name, email, age)
                    VALUES(?, ?, ?)
                    """;

        try(
            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected == 1) {
                System.out.println("Student created");
            } else {
                System.out.println("Failed to create student");
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student, Long id) {

        String sql = """
                UPDATE jdbc_student 
                SET name = ?,
                    email = ?,
                    age = ?
                WHERE id = ?
                """;

        try(
            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setLong(4, id);

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected == 1) {
                System.out.println("Update operation successfull.");
            } else {
                System.out.println("Updation failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long id) {

        String sql = """
                DELETE FROM jdbc_student
                WHERE id = ?
                """;

        try(
            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            
            preparedStatement.setLong(1, id);

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected == 1) {
                System.out.println("Delete operation successfull");
            } else {
                System.out.println("Deletation failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    public void getStudentById(Long id) {

        String sql = "SELECT * FROM jdbc_student " + 
                         "WHERE id=?";

        try(
            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()) {
                    Student student = mapRow(resultSet);
                    System.out.println(student);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void getStudents() {

        String sql = "SELECT * FROM jdbc_student";

        try(
            Connection connection = DriverManager.getConnection(url, username, pass);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {

            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                List<Student> studentList = new ArrayList<>();

                while(resultSet.next()) {
                    Student student = mapRow(resultSet);
                    studentList.add(student);
                    System.out.println(student);
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void completeCRUD() {
        try {
            Connection connection = DriverManager.getConnection(url, username, pass);
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM jdbc_student WHERE id=2";

            boolean result = statement.execute(sql);

            if(result) {
                // ResultSet resultSet = statement.getResultSet();
                statement.getResultSet();
            } else {
                // int rowAffected = statement.getUpdateCount();
                statement.getUpdateCount();
            }

            connection.close();;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Student mapRow(ResultSet resultSet) {
        Student student = new Student();
        try {
            student.setId(resultSet.getLong("id"));
            student.setName(resultSet.getString("name"));
            student.setEmail(resultSet.getString("email"));
            student.setAge(resultSet.getInt("age"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }
}
