package com.figueiredoisaac.schoolmanager.repository.entities;

import com.figueiredoisaac.schoolmanager.commons.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.course.model.Course;
import jakarta.persistence.*;

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
    @Column(length = 10, nullable = false, unique = true)
    private String code;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id" , nullable = false)
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

    public CourseEntity() {}

    public CourseEntity(Long id, String name, String code,
                        UserEntity instructor, String description, CourseStatus status,
                        LocalDateTime createdAt, LocalDateTime disabledAt) {
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

    public static CourseEntity from(Course course){
        return new CourseEntity(
            course.getId(),
            course.getName(),
            course.getCode(),
            UserEntity.from(course.getInstructor()),
            course.getDescription(),
            course.getStatus(),
            course.getCreatedAt(),
            course.getDisabledAt()
        );
    }

    public Course toModel() {
        return new Course(
          this.id,
          this.name,
          this.code,
          this.instructor.toModel(),
          this.description,
          this.status,
          this.createdAt,
          this.disabledAt
        );

    }

}
