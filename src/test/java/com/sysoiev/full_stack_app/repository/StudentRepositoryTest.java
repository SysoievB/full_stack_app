package com.sysoiev.full_stack_app.repository;

import com.sysoiev.full_stack_app.model.Gender;
import com.sysoiev.full_stack_app.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTestStudentRepository;

    @Test
    void itShouldCheckWhenStudentEmailExists() {
        // Given
        String email = "ivan@mail.com";
        Student student = new Student("Ivan", email, Gender.MALE);

        // When
        underTestStudentRepository.save(student);

        // Then
        boolean expected = underTestStudentRepository.selectExistsEmail(email);
        assertThat(expected).isTrue();
    }
}