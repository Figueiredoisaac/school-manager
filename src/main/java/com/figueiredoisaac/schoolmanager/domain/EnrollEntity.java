package com.figueiredoisaac.schoolmanager.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ENROLL",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "course_id"}, name = "uk_user_course"))
public class EnrollEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;
    @Column(name = "enrolled_at")
    private LocalDateTime enrolledAt;

    public EnrollEntity() {
    }

    public EnrollEntity(UserEntity user, CourseEntity course, LocalDateTime enrolledAt) {
        this.user = user;
        this.course = course;
        this.enrolledAt = enrolledAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setCourse(CourseEntity course) {
        this.course = course;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public CourseEntity getCourse() {
        return course;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
}
