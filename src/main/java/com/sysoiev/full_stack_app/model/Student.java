package com.sysoiev.full_stack_app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    private Long id;
    private String name;
    private String email;
    private Gender gender;
}
