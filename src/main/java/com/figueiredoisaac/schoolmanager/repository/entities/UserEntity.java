package com.figueiredoisaac.schoolmanager.repository.entities;

import com.figueiredoisaac.schoolmanager.commons.UserRoles;
import com.figueiredoisaac.schoolmanager.domain.user.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 20 , nullable = false, unique = true)
    private String username;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRoles role;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public UserEntity() {}

    public UserEntity(Long id, String name, String username, String email,
                      String password, UserRoles role, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static UserEntity from(User user){

        return new UserEntity(
        user.getId(),
        user.getName(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        user.getRole(),
        user.getCreatedAt()
        );

    }

    public User toModel() {
        return new User(
            this.id,
            this.name,
            this.username,
            this.email,
            this.password,
            this.role,
            this.createdAt
        );
    }
}
