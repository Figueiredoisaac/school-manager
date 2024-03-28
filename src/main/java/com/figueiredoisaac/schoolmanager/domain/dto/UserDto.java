package com.figueiredoisaac.schoolmanager.domain.dto;

import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;

import java.time.LocalDateTime;

public class UserDto {
    private Long id;
    private String name;

    private String username;

    private String email;

    private String password;

    private UserRoles role;

    private LocalDateTime createdAt;

    public UserDto() {
        setCreatedAt(LocalDateTime.now());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

