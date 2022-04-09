package com.sysoiev.full_stack_app.controller;

import com.sysoiev.full_stack_app.model.Gender;
import com.sysoiev.full_stack_app.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @GetMapping
    public List<Student> getAllStudents() {

        return List.of(
                new Student(1L, "jane", "Jane@mail.com", Gender.FEMALE),
                new Student(2L, "ivan", "ival@mail.com", Gender.MALE),
                new Student(3L, "dan", "dan@mail.com", Gender.MALE)
        );
    }
}
