package com.figueiredoisaac.schoolmanager.domain;

import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "COURSE")
public class CourseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Pattern(regexp = "^[a-z](?:[a-z\\-]*[a-z])?$", message = "Code deve conter apenas caracteres minúsculos e '-' usado como separador ")
    @Column(length = 10, nullable = false, unique = true)
    private String code;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", nullable = false)
    private UserEntity instructor;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseStatus status;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column
    private LocalDateTime disabledAt;

    public CourseEntity() {
    }

    public CourseEntity(Long id, String name, String code, UserEntity instructor, String description,
                        CourseStatus status, LocalDateTime createdAt, LocalDateTime disabledAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.disabledAt = disabledAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setInstructor(UserEntity instructor) {
        this.instructor = instructor;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDisabledAt(LocalDateTime disabledAt) {
        this.disabledAt = disabledAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public UserEntity getInstructor() {
        return instructor;
    }

    public String getDescription() {
        return description;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }
}
