package com.figueiredoisaac.schoolmanager.domain.course.model;

import com.figueiredoisaac.schoolmanager.commons.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.user.model.User;
import jakarta.validation.constraints.Pattern;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Course implements Serializable {


    private Long id;

    private String name;
    @Pattern(regexp = "^[a-z](?:[a-z\\-]*[a-z])?$", message = "Code deve conter apenas caracteres minúsculos e '-' usado como separador ")
    private String code;

    private User instructor;

    private String description;

    private CourseStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime disabledAt;

    public Course(Long id, String name, String code, User instructor,
                  String description, CourseStatus status, LocalDateTime createdAt,
                  LocalDateTime disabledAt) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.disabledAt = disabledAt;
    }

    public Course(String name, String code, User instructor, String description,
                  CourseStatus status, LocalDateTime createdAt) {
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.description = description;
        this.status = status;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }

    public void setDisabledAt(LocalDateTime disabledAt) {
        this.disabledAt = disabledAt;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", instructor=" + instructor +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", disabledAt=" + disabledAt +
                '}';
    }
}
