package com.figueiredoisaac.schoolmanager.controller.course;

import com.figueiredoisaac.schoolmanager.commons.CourseStatus;
import com.figueiredoisaac.schoolmanager.commons.UserRoles;
import com.figueiredoisaac.schoolmanager.commons.exceptions.BadRequestException;
import com.figueiredoisaac.schoolmanager.domain.course.model.Course;
import com.figueiredoisaac.schoolmanager.domain.course.usecase.CourseUseCase;
import com.figueiredoisaac.schoolmanager.domain.user.model.User;
import com.figueiredoisaac.schoolmanager.domain.user.usecase.UserUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.figueiredoisaac.schoolmanager.commons.CourseStatus.ATIVO;
import static com.figueiredoisaac.schoolmanager.commons.CourseStatus.INATIVO;

@RestController
@RequestMapping("/v1/course")
public class CourseController {
    private final CourseUseCase courseUseCase;
    private final UserUseCase userUseCase;

    public CourseController(
            CourseUseCase courseUseCase,
            UserUseCase userUseCase
    ) {
        this.courseUseCase = courseUseCase;
        this.userUseCase = userUseCase;
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<Course>> findAllByStatus(
            @RequestParam(name = "status", required = false) CourseStatus status,
            @RequestParam(name = "p") Integer page
    ) throws Exception
    {
        if (status == ATIVO || status == INATIVO) {
            return new ResponseEntity<>(courseUseCase.findAllByStatus(status, PageRequest.of(page-1 ,10)),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(courseUseCase.findAll(PageRequest.of(page-1 ,10)),HttpStatus.OK);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<CourseCreateOutput> create(
            @Valid @RequestBody CourseCreateInput input
    ) throws Exception {

        User instructor = userUseCase.findByUsername(input.getInstructor());

        if (instructor.getRole()!= UserRoles.INSTRUTOR){
            throw new BadRequestException("Usuário informado não é Instrutor");
        }

        Course course = new Course(
                input.getName(),
                input.getCode(),
                instructor,
                input.getDescription(),
                input.getStatus(),
                input.getCreatedAt()
        );
        Course response = courseUseCase.create(course);

        return new ResponseEntity(
                new CourseCreateOutput(
                        response.getName(),
                        response.getCode(),
                        response.getDescription(),
                        response.getInstructor().getName(),
                        response.getStatus(),
                        response.getCreatedAt()
                ),
                HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{code}/{active}")
    public ResponseEntity<CourseStatusOutput> findAllByStatus(
            @PathVariable("code") String code,
            @PathVariable("active") Boolean active
    ) throws Exception
    {
            Course response = courseUseCase.changeStatus(code, active);
        return new ResponseEntity<>(
                new CourseStatusOutput(
                    response.getName(),
                    response.getCode(),
                    response.getStatus(),
                    response.getCreatedAt(),
                    response.getDisabledAt()
                ),
                HttpStatus.OK);
    }

}

class CourseCreateInput {
    @NotBlank
    private String name;
    @NotBlank
    @Size(max = 10, message = "O campo deve ter no máximo 10 caracteres")
    @Pattern(regexp = "^[a-z](?:[a-z\\-]*[a-z])?$", message = "Code deve conter apenas caracteres minúsculos e '-' usado como separador ")
    private String code;

    private String instructor;
    @NotBlank
    private String description;

    private CourseStatus status;

    private LocalDateTime createdAt;

    public CourseCreateInput(String name, String code, String instructor, String description) {
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.description = description;
        this.status = ATIVO;
        this.createdAt = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDescription() {
        return description;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}

class CourseCreateOutput {

    private String name;
    private String code;
    private String instructor;
    private String description;
    private CourseStatus status;
    private LocalDateTime createdAt;

    public CourseCreateOutput(String name, String code, String instructor, String description,
                              CourseStatus status, LocalDateTime createdAt) {
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getDescription() {
        return description;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


}

class CourseStatusOutput {

    private String name;
    private String code;
    private CourseStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime disabledAt;

    public CourseStatusOutput(String name, String code, CourseStatus status,
                              LocalDateTime createdAt, LocalDateTime disabledAt) {
        this.name = name;
        this.code = code;
        this.status = status;
        this.createdAt = createdAt;
        this.disabledAt = disabledAt;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDisabledAt() {
        return disabledAt;
    }
}
