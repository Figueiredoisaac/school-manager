package com.figueiredoisaac.schoolmanager.domain.dto.output;

import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;

import java.time.LocalDateTime;

public class CourseDTtoOutput {
    private Long id;

    private String name;

    private String code;

    private UserDtoOutput instructor;

    private String description;

    private CourseStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime disabledAt;

    public CourseDTtoOutput() {
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

    public String getCode() {
        return code;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public UserDtoOutput getInstructor() {
        return instructor;
    }

    public void setInstructor(UserDtoOutput instructor) {
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
}
