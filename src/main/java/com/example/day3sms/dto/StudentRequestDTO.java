package com.example.day3sms.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO {

        private String name;      // nullable → PATCH safe
        private Integer age;      // Integer (NOT int) → PATCH safe
        private String email;     // nullable → PATCH safe
}
