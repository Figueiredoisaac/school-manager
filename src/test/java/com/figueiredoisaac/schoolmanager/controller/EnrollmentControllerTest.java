package com.figueiredoisaac.schoolmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;
import com.figueiredoisaac.schoolmanager.domain.record.input.CourseRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.input.EnrollRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.input.UserRecordInput;
import com.figueiredoisaac.schoolmanager.service.CourseService;
import com.figueiredoisaac.schoolmanager.service.EnrollService;
import com.figueiredoisaac.schoolmanager.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EnrollmentControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @Mock
    private EnrollService enrollService;

    @Test
    void should_enroll_user_in_course() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ESTUDANTE,
                LocalDateTime.now());
        userService.create(userTest);

        CourseRecordInput courseRecordTest = new CourseRecordInput(
                "Java",
                "crs",
                userTest.username(),
                "Curso de Java"
        );
        courseService.create(courseRecordTest);

        EnrollRecordInput enrollTestInput = new EnrollRecordInput(
                userTest.username(),
                courseRecordTest.code()
        );

        mockMvc.perform(post("/enroll/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(enrollTestInput)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_not_enroll_user_already_enrolled() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ESTUDANTE,
                LocalDateTime.now());
        userService.create(userTest);

        CourseRecordInput courseRecordTest = new CourseRecordInput(
                "Java",
                "crs",
                userTest.username(),
                "Curso de Java"
        );
        courseService.create(courseRecordTest);

        enrollService.enrollment(
                new EnrollRecordInput(
                        userTest.username(),
                        courseRecordTest.code()
                )
        );

        EnrollRecordInput enrollTestInput = new EnrollRecordInput(
                userTest.username(),
                courseRecordTest.code()
        );

        mockMvc.perform(post("/enroll/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(enrollTestInput)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void should_not_enroll_unknown_user() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ESTUDANTE,
                LocalDateTime.now());
        userService.create(userTest);

        CourseRecordInput courseRecordTest = new CourseRecordInput(
                "Java",
                "crs",
                userTest.username(),
                "Curso de Java"
        );
        courseService.create(courseRecordTest);

        EnrollRecordInput enrollTestInput = new EnrollRecordInput(
                "unknown-user",
                courseRecordTest.code()
        );

        mockMvc.perform(post("/enroll/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(enrollTestInput)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void should_not_enroll_unknown_course() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ESTUDANTE,
                LocalDateTime.now());
        userService.create(userTest);

        CourseRecordInput courseRecordTest = new CourseRecordInput(
                "Java",
                "crs",
                userTest.username(),
                "Curso de Java"
        );
        courseService.create(courseRecordTest);

        EnrollRecordInput enrollTestInput = new EnrollRecordInput(
                userTest.username(),
                "unknowcode"
        );

        mockMvc.perform(post("/enroll/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(enrollTestInput)))
                .andExpect(status().isBadRequest());
    }

}
