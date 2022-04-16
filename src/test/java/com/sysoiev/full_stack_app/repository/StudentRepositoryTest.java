package com.sysoiev.full_stack_app.repository;

import com.sysoiev.full_stack_app.model.Gender;
import com.sysoiev.full_stack_app.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTestStudentRepository;

    @AfterEach
    void tearDown() {
        underTestStudentRepository.deleteAll();
    }

    @Test
    void itShouldCheckWhenStudentEmailExists() {
        // Given
        String email = "ivan@mail.com";
        var student = new Student("Ivan", email, Gender.MALE);

        // When
        underTestStudentRepository.save(student);

        // Then
        boolean expected = underTestStudentRepository.selectExistsEmail(email);
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckWhenStudentEmailDoesNotExist() {
        // Given
        String email = "ivan@mail.com";

        // When
        boolean expected = underTestStudentRepository.selectExistsEmail(email);

        // Then
        assertThat(expected).isFalse();
    }
}