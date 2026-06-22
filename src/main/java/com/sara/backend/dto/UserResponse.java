package com.sara.backend.dto;

import com.sara.backend.Role;

public class UserResponse {
    private int id;
    private String name;
    private int age;
    private String email;
    private Role role;

    public UserResponse(int id, String name, int age, String email, Role role ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public String getEmail() {
        return email;
    }
    public Role getRole() {
        return role;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    }

