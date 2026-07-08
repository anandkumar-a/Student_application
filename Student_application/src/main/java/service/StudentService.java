package com.demo.studentapp.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.demo.studentapp.entity.Student;
import com.demo.studentapp.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Save a new student
    public Student saveStudent(Student student) {
        Objects.requireNonNull(student, "student must not be null");
        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        Objects.requireNonNull(id, "id must not be null");
        Optional<Student> student = studentRepository.findById(id);

        return student.orElse(null);
    }

    // Update student
    public Student updateStudent(Student student) {
        Objects.requireNonNull(student, "student must not be null");
        return studentRepository.save(student);
    }

    // Delete student
    public void deleteStudent(Long id) {
        Objects.requireNonNull(id, "id must not be null");
        studentRepository.deleteById(id);
    }

    // Search students by name
    public List<Student> searchStudents(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    // Search students by department
    public List<Student> getStudentsByDepartment(String department) {
        return studentRepository.findByDepartment(department);
    }

    // Find student by email
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    // Check if email already exists
    public boolean emailExists(String email) {
        return studentRepository.existsByEmail(email);
    }

    // Get total student count
    public long getTotalStudents() {
        return studentRepository.count();
    }
}