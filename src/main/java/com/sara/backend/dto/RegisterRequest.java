package com.sara.backend.dto;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank(message = "Name boş ola bilməz")

    private String name;

    private int age;

    @Email(message = "Email düzgün deyil")

    @NotBlank(message = "Email boş ola bilməz")

    private String email;

    @NotBlank(message = "Password boş ola bilməz")

    private String password;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;

    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
