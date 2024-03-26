package com.figueiredoisaac.schoolmanager.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.time.LocalDateTime;

@Entity
@Table(name = "RATING")
public class RatingEntity {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rating;
    private UserEntity user;
    private CourseEntity course;
    private String description;
    private LocalDateTime createdAt;

    public RatingEntity(Long id, Integer rating, UserEntity user, CourseEntity course, String description, LocalDateTime createdAt) {
        this.id = id;
        this.rating = rating;
        this.user = user;
        this.course = course;
        this.description = description;
        this.createdAt = createdAt;
    }

    public RatingEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
