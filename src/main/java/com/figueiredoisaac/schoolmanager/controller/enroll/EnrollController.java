package com.figueiredoisaac.schoolmanager.controller.enroll;

import com.figueiredoisaac.schoolmanager.commons.CourseStatus;
import com.figueiredoisaac.schoolmanager.commons.exceptions.BadRequestException;
import com.figueiredoisaac.schoolmanager.domain.course.model.Course;
import com.figueiredoisaac.schoolmanager.domain.course.usecase.CourseUseCase;
import com.figueiredoisaac.schoolmanager.domain.enroll.model.Enroll;
import com.figueiredoisaac.schoolmanager.domain.enroll.usecase.EnrollUseCase;
import com.figueiredoisaac.schoolmanager.domain.user.model.User;
import com.figueiredoisaac.schoolmanager.domain.user.usecase.UserUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/enroll")
public class EnrollController {

    private final CourseUseCase courseUseCase;
    private final UserUseCase userUseCase;
    private final EnrollUseCase enrollUseCase;

    public EnrollController(
            CourseUseCase courseUseCase,
            UserUseCase userUseCase,
            EnrollUseCase enrollUseCase
    ) {
        this.courseUseCase = courseUseCase;
        this.userUseCase = userUseCase;
        this.enrollUseCase = enrollUseCase;
    }

    @PostMapping(value = "/new")
    public ResponseEntity<EnrollOutput> enrollment(
            @Valid @RequestBody EnrollInput input
    ) throws Exception
    {
        User user = userUseCase.findByUsername(input.getUsername());
        Course course = courseUseCase.findByCode(input.getCourseCode());

        if (course.getStatus() == CourseStatus.INATIVO) {
            throw new BadRequestException("Curso não permite matrículas novas");}

        Enroll enroll = enrollUseCase.enrollment(new Enroll(
                user,
                course,
                input.getEnrolledAt()
            )
        );
        return new ResponseEntity(
                new EnrollOutput(
                     enroll.getId(),
                     enroll.getUser().getName(),
                     enroll.getCourse().getName(),
                     enroll.getEnrolledAt()
                ),
                HttpStatus.OK);
    }



}

class EnrollInput {

    @NotBlank
    @Size(max = 20, message = "O campo deve ter no máximo 20 caracteres")
    @Pattern(regexp = "^[a-z](?:[a-z\\-]*[a-z])?$", message = "Username deve conter apenas caracteres minúsculos e '-' usado como separador ")
    private String username;
    @NotBlank
    @Size(max = 10, message = "O campo deve ter no máximo 10 caracteres")
    @Pattern(regexp = "^[a-z](?:[a-z\\-]*[a-z])?$", message = "Code deve conter apenas caracteres minúsculos e '-' usado como separador ")
    private String courseCode;
    private LocalDateTime enrolledAt;

    public EnrollInput(String username, String courseCode) {
        this.username = username;
        this.courseCode = courseCode;
        this.enrolledAt = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
}

class EnrollOutput {

    private Long id;
    private String user;
    private String course;
    private LocalDateTime enrolledAt;

    public EnrollOutput(Long id, String user, String course, LocalDateTime enrolledAt) {
        this.id = id;
        this.user = user;
        this.course = course;
        this.enrolledAt = enrolledAt;
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getCourse() {
        return course;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }
}
