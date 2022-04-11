package com.sysoiev.full_stack_app.service;

import com.sysoiev.full_stack_app.model.Student;
import com.sysoiev.full_stack_app.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }
}
