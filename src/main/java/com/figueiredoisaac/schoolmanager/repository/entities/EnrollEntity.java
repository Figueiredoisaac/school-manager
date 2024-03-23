package com.figueiredoisaac.schoolmanager.repository.entities;

import com.figueiredoisaac.schoolmanager.domain.enroll.model.Enroll;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "ENROLL")
public class EnrollEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity courseEntity;
    @Column(name = "enrolled_at")
    private LocalDateTime enrolledAt;

    public EnrollEntity() {}

    public EnrollEntity(Long id, UserEntity userEntity,
                        CourseEntity courseEntity, LocalDateTime enrolledAt) {
        this.id = id;
        this.userEntity = userEntity;
        this.courseEntity = courseEntity;
        this.enrolledAt = enrolledAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public EnrollEntity from(Enroll enroll){
        return new EnrollEntity(
                enroll.getId(),
                UserEntity.from(enroll.getUser()),
                CourseEntity.from(enroll.getCourse()),
                enroll.getEnrolledAt()
        );
    }
}
