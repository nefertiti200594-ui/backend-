package com.sara.backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

public class UserRequest {
    @NotBlank(message = "Name boş ola bilməz")
    private String name;

    private int age;

    @Email(message = "Email düzgün deyil")

    @NotBlank(message = "Email boş ola bilməz")

    private String email;

    public UserRequest() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }
    public class RegisterRequest {

        @NotBlank(message = "Name boş ola bilməz")

        private String name;

        private int age;

        @Email(message = "Email düzgün deyil")

        @NotBlank(message = "Email boş ola bilməz")

        private String email;

        @NotBlank(message = "Password boş ola bilməz")

        private String password;

        public RegisterRequest() {
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getEmail() {
            return email;

        }

        public String getPassword() {
            return password;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        public void setPassword(String password) {
        this.password = password;}
    }

    public class LoginRequest {

        @Email(message = "Email düzgün deyil")

        @NotBlank(message = "Email boş ola bilməz")

        private String email;

        @NotBlank(message = "Password boş ola bilməz")

        private String password;

        public LoginRequest() {
        }
        public String getEmail() {
            return email;
        }
        public String getPassword() {
            return password;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public void setPassword(String password) {
            this.password = password;
        }
    }

    }

