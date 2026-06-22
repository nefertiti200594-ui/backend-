package com.sara.backend.dto;
import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @Email(message = "Email düzgün deyil")

    @NotBlank(message = "Email boş ola bilməz")

    private String email;

    @NotBlank(message = "Password boş ola bilməz")

    private String password;

    public String getEmail()
    { return email; }

    public String getPassword()
    { return password; }

    public void setEmail(String email)
    { this.email = email; }

    public void setPassword(String password)
    { this.password = password; }

}
