package com.sysoiev.full_stack_app.service;

import com.sysoiev.full_stack_app.exception.BadRequestException;
import com.sysoiev.full_stack_app.exception.StudentNotFoundException;
import com.sysoiev.full_stack_app.model.Gender;
import com.sysoiev.full_stack_app.model.Student;
import com.sysoiev.full_stack_app.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTestStudentService;

    @BeforeEach
    void setUp() {
        underTestStudentService = new StudentService(studentRepository);
    }

    @Test
    void itShouldNotGetAll() {
        // When
        underTestStudentService.getAll();

        // Then
        verify(studentRepository).findAll();
    }

    @Test
    void itShouldAdd() {
        // Given
        var student = new Student("Ivan", "ivan@mail.com", Gender.MALE);

        // When
        underTestStudentService.add(student);

        // Then
        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(studentArgumentCaptor.capture());

        var capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(student);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        // Given
        var student = new Student("Ivan", "ivan@mail.com", Gender.MALE);
        given(studentRepository.selectExistsEmail(student.getEmail())).willReturn(true);

        // Then
        assertThatThrownBy(() -> underTestStudentService.add(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + student.getEmail() + " taken");

        verify(studentRepository, never()).save(any());
    }

    @Test
    void itShouldDeleteStudent() {
        // Given
        Long id = 10L;
        given(studentRepository.existsById(id)).willReturn(true);

        // When
        underTestStudentService.delete(id);

        // Then
        verify(studentRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeleteStudentNotFound() {
        // Given
        long id = 10;
        given(studentRepository.existsById(id)).willReturn(false);

        // Then
        assertThatThrownBy(() -> underTestStudentService.delete(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + id + " does not exists");

        verify(studentRepository, never()).deleteById(any());
    }

    @Test
    void itShouldUpdateStudent() {
        // Given
        long id = 1;
        var student = new Student(id, "Ivan", "ivan@mail.com", Gender.MALE);
        var updatedStudent = new Student("update", "update@mail.com", Gender.MALE);

        given(studentRepository.existsById(id)).willReturn(true);
        given(studentRepository.findById(id)).willReturn(Optional.of(student));

        // When
        underTestStudentService.add(student);
        underTestStudentService.update(id, updatedStudent);

        // Then
        verify(studentRepository, atLeastOnce()).save(student);
        verify(studentRepository, atLeastOnce()).findById(id);

        Optional<Student> optionalStudent = studentRepository.findById(id);
        assertThat(optionalStudent)
                .isPresent()
                .hasValueSatisfying(s -> {
                    assertThat(s.getName()).isEqualTo(updatedStudent.getName());
                    assertThat(s.getEmail()).isEqualTo(updatedStudent.getEmail());
                    assertThat(s.getGender().name()).isEqualTo(updatedStudent.getGender().name());
                });
    }

    @Test
    void willThrowWhenUpdateStudentNotFound() {
        // Given
        long id = 10;
        var student = new Student("update", "update@mail.com", Gender.MALE);

        given(studentRepository.existsById(id)).willReturn(false);

        // Then
        assertThatThrownBy(() -> underTestStudentService.update(id, student))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("Student with id " + id + " does not exists");

        verify(studentRepository, never()).deleteById(any());
    }
}