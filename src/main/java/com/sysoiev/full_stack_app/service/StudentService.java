package com.sysoiev.full_stack_app.service;

import com.sysoiev.full_stack_app.exception.BadRequestException;
import com.sysoiev.full_stack_app.exception.StudentNotFoundException;
import com.sysoiev.full_stack_app.model.Student;
import com.sysoiev.full_stack_app.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAll() {

        return studentRepository.findAll();
    }

    public void add(Student student) {
        // check if email is taken
        Boolean existsEmail = studentRepository
                .selectExistsEmail(student.getEmail());
        if (existsEmail) {
            throw new BadRequestException(
                    "Email " + student.getEmail() + " taken");
        }

        studentRepository.save(student);
    }

    public void delete(Long studentId) {
        // check if student exists
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(
                    "Student with id " + studentId + " does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    public void update(Long studentId, Student updatedStudent) {
        // check if student exists
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(
                    "Student with id " + studentId + " does not exists");
        }

        Student student = studentRepository.getOne(studentId);

        if (updatedStudent.getName() != null) student.setName(updatedStudent.getName());

        if (updatedStudent.getEmail() != null) student.setEmail(updatedStudent.getEmail());

        if (updatedStudent.getGender() != null) student.setGender(updatedStudent.getGender());

        studentRepository.save(student);
    }
}
