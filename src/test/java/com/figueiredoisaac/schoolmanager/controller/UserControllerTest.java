package com.figueiredoisaac.schoolmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;
import com.figueiredoisaac.schoolmanager.domain.record.UserRecord;
import com.figueiredoisaac.schoolmanager.domain.record.input.UserRecordInput;
import com.figueiredoisaac.schoolmanager.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void should_retrieve_user_by_username() throws Exception {
        UserRecord userTest = Mockito.mock(UserRecord.class);
        Mockito.when(userService.findByUsername(userTest.username())).then(invocationOnMock -> userTest);

        mockMvc.perform(get("/users/"+userTest.username())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(userTest.name()))
                .andExpect(jsonPath("$.email").value(userTest.email()))
                .andExpect(jsonPath("$.role").value(userTest.role()));
    }

    @Test
    void not_found_when_user_does_not_exist() throws Exception {
        mockMvc.perform(get("/users/non-existent")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_add_new_user() throws Exception {
        UserRecord userTest = Mockito.mock(UserRecord.class);
        UserRecordInput create = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ADMIN,
                null);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(create)))
                .andExpect(status().isCreated());
    }

    @ParameterizedTest
    @CsvSource({
            ", maria@email.com",
            "'', maria@email.com",
            "'    ', maria@email.com",
            "maria, ",
            "maria, ''",
            "maria, '    '",
            "maria, not-an-email",
            "an-username-that-is-really-really-big , maria@email.com"
    })
    void should_validate_bad_user_requests(String username, String email) throws Exception {
        UserRecordInput create = new UserRecordInput(
                "Maria",
                username,
                email,
                "12345678",
                UserRoles.ESTUDANTE,
                null);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(create)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_allow_duplication_of_username() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ESTUDANTE,
                LocalDateTime.now());
        userService.create(userTest);

        UserRecordInput create = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti2@astconsult.com.br",
                "12345678",
                UserRoles.ADMIN,
                null);

        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(create)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_not_allow_duplication_of_email() throws Exception {
        UserRecordInput userTest = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ESTUDANTE,
                LocalDateTime.now());
        userService.create(userTest);

        UserRecordInput create = new UserRecordInput(
                "André Lucas Rodrigo Cavalcanti",
                "andre-lucasrc2",
                "andre_lucas_cavalcanti@astconsult.com.br",
                "12345678",
                UserRoles.ADMIN,
                null);


        mockMvc.perform(post("/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(create)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}