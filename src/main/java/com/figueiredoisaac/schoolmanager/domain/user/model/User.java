package com.figueiredoisaac.schoolmanager.domain.user.model;

import com.figueiredoisaac.schoolmanager.commons.UserRoles;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class User {

    private Long id;

    private String name;


    @Pattern(regexp = "^[a-z](?:[a-z\\-]*[a-z])?$", message = "Username deve conter apenas caracteres minúsculos e '-' usado como separador ")
    private String username;

    @Email
    private String email;

    private String password;

    private UserRoles role;

    private LocalDateTime createdAt;

    public User(Long id, String name, String username, String email, String password, UserRoles role, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public User(String name, String username, String email, String password, UserRoles role, LocalDateTime createdAt) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createdAt=" + createdAt +
                '}';
    }
}
