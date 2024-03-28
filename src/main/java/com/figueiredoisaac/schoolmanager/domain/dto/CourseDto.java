package com.figueiredoisaac.schoolmanager.domain.dto;

import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;

import java.time.LocalDateTime;

public class CourseDto {
    private Long id;

    private String name;

    private String code;

    private String instructor;

    private String description;

    private CourseStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime disabledAt;

    public CourseDto() {
        setStatus(CourseStatus.ATIVO);
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
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
