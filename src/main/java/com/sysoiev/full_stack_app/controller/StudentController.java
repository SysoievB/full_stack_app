package com.sysoiev.full_stack_app.controller;

import com.sysoiev.full_stack_app.model.Student;
import com.sysoiev.full_stack_app.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {

        return studentService.getAll();
    }

    @PostMapping
    public void addStudent(@Valid @RequestBody Student student) {
        studentService.add(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody Student student) {
        studentService.update(id, student);
    }
}
