package com.sysoiev.full_stack_app.model;

import lombok.Data;

@Data
public class Student {

    private Long id;
    private String name;
    private String email;
    private Gender gender;
}
