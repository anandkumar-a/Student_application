package controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.demo.studentapp.entity.Student;
import com.demo.studentapp.service.StudentService;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
   
    // View All Students
    @GetMapping("/students")
    public String viewStudents(Model model) {

        List<Student> students = studentService.getAllStudents();

        model.addAttribute("students", students);

        return "view-students";
    }

    // Open Add Student Page
    @GetMapping("/students/add")
    public String addStudentPage(Model model) {

        model.addAttribute("student", new Student());

        return "add-student";
    }

    // Save Student
    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute Student student) {

        studentService.saveStudent(student);

        return "redirect:/students";
    }

    // Open Edit Student Page
    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id,
                              Model model) {

        Student student = studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "edit-student";
    }

    // Update Student
    @PostMapping("/students/update")
    public String updateStudent(@ModelAttribute Student student) {

        studentService.updateStudent(student);

        return "redirect:/students";
    }

    // Delete Student
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/students";
    }

    // Search Student
    @GetMapping("/students/search")
    public String searchStudent(@RequestParam("name") String name,
                                Model model) {

        List<Student> students = studentService.searchStudents(name);

        model.addAttribute("students", students);

        return "view-students";
    }

}