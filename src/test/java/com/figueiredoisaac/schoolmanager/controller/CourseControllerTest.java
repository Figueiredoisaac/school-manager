package com.figueiredoisaac.schoolmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;
import com.figueiredoisaac.schoolmanager.domain.record.CourseRecord;
import com.figueiredoisaac.schoolmanager.domain.record.input.CourseRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.input.UserRecordInput;
import com.figueiredoisaac.schoolmanager.service.CourseService;
import com.figueiredoisaac.schoolmanager.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CourseControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private CourseService courseService;

    @Test
    void should_retrieve_all_courses() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.INSTRUTOR,
                  null);
        userService.create(userTest);

        CourseRecordInput courseRecordTest = new CourseRecordInput(
                "Java",
                "crs-java",
                userTest.username(),
                "Curso de Java"
        );
        courseService.create(courseRecordTest);

        CourseRecordInput courseRecordTest2 = new CourseRecordInput(
                "C+",
                "crs-cmais",
                userTest.username(),
                "Curso de C+"
        );
        courseService.create(courseRecordTest2);

        mockMvc.perform(get("/courses")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].code", is("crs-java")))
                .andExpect(jsonPath("$[0].name", is("Java")))
                .andExpect(jsonPath("$[0].shortDescription", is(userTest.name())))
                .andExpect(jsonPath("$[1].code", is("crs-cmais")))
                .andExpect(jsonPath("$[1].name", is("C+")))
                .andExpect(jsonPath("$[1].shortDescription", is(userTest.name())));
    }

    @Test
    void should_add_new_course() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.INSTRUTOR,
                  null);
        userService.create(userTest);

        CourseRecordInput courseTest = new CourseRecordInput(
                "C+",
                "crs-cmais",
                userTest.username(),
                "Curso de C+");

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(courseTest)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_not_add_new_course_duplicate() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.INSTRUTOR,
                  null);
        userService.create(userTest);

        CourseRecordInput courseRecordTest = new CourseRecordInput(
                "Java",
                "crs",
                userTest.username(),
                "Curso de Java"
        );
        courseService.create(courseRecordTest);

        CourseRecordInput courseTest2 = new CourseRecordInput(
                "C+",
                "crs",
                "andre-lucasrc",
                "Curso de C+");

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(courseTest2)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_add_new_course_user_not_instructor() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ADMIN,
                  null);
        userService.create(userTest);

        CourseRecordInput courseTest = new CourseRecordInput(
                "C+",
                "crs-cmais",
                userTest.username(),
                "Curso de C+");

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(courseTest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @CsvSource({
            "notAllowed",
            "-notallowe",
            "notallowe-",
            "1notallowe",
            "!notallowe",
            "codenotallowed",
    })
    void should_not_add_new_course_code_not_allowed(String code) throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ADMIN,
                  null);
        userService.create(userTest);

        CourseRecordInput courseTest = new CourseRecordInput(
                "C+",
                code,
                userTest.username(),
                "Curso de C+");

        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(courseTest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_change_course_status_not_active() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.INSTRUTOR,
                  null);

        CourseRecordInput courseRecordTest = new CourseRecordInput(
                "Java",
                "crs",
                userTest.username(),
                "Curso de Java"
        );


        mockMvc.perform(post("/courses/" + courseRecordTest.code() + "false")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("INATIVO")))
                .andExpect(jsonPath("$.disabledAt", is(notNullValue())));
    }

    @Test
    void should_change_course_status_active() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.INSTRUTOR,
                  null);

        CourseRecordInput courseRecordTest = new CourseRecordInput(
                "Java",
                "crs",
                userTest.username(),
                "Curso de Java"
        );


        mockMvc.perform(post("/courses/" + courseRecordTest.code() + "false")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("ATIVO")))
                .andExpect(jsonPath("$.disabledAt", is(null)));
    }
}