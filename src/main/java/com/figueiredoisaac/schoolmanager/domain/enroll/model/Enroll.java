package com.figueiredoisaac.schoolmanager.domain.enroll.model;

import com.figueiredoisaac.schoolmanager.domain.course.model.Course;
import com.figueiredoisaac.schoolmanager.domain.user.model.User;

import java.time.LocalDateTime;

public class Enroll {

    private Long id;
    private User user;
    private Course course;
    private LocalDateTime enrolledAt;

    public Enroll(Long id, User user, Course course, LocalDateTime enrolledAt) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.enrolledAt = enrolledAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    @Override
    public String toString() {
        return "Enroll{" +
                "id=" + id +
                ", user=" + user +
                ", course=" + course +
                ", enrolledAt=" + enrolledAt +
                '}';
    }
}
