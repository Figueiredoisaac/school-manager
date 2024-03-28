package com.figueiredoisaac.schoolmanager.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "rating",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"}, name = "uk_user_course_rating"))
public class RatingEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rating", nullable = false)
    private Long rating;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private CourseEntity course;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public RatingEntity() {
    }

    public RatingEntity(Long id, Long rating, UserEntity user,
                        CourseEntity course, String description, LocalDateTime createdAt) {
        this.id = id;
        this.rating = rating;
        this.user = user;
        this.course = course;
        this.description = description;
        this.createdAt = createdAt;
    }
    public UserEntity getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
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
