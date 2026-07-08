package com.demo.studentapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.studentapp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Search student by name
    List<Student> findByNameContainingIgnoreCase(String name);

    // Search student by department
    List<Student> findByDepartment(String department);

    // Find student by email
    Student findByEmail(String email);

    // Check whether email already exists
    boolean existsByEmail(String email);
}