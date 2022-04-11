package com.sysoiev.full_stack_app.controller;

import com.sysoiev.full_stack_app.model.Student;
import com.sysoiev.full_stack_app.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {

        return studentService.getAllStudents();
    }
}
