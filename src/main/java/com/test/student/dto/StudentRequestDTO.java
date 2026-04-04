package com.test.student.dto;

import jakarta.validation.constraints.*;

// request your spring api getting from browser or postman
public class StudentRequestDTO {

    @Size(min=3,message = "name should be atleast 3 characters")
    private String name;

    @Email(message="Invalid email format")
    @NotBlank(message = "Email should be present")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 65, message = "Age cannot exceed 65")
    private Integer age;

    public StudentRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
