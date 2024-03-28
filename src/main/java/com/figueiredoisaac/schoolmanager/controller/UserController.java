package com.figueiredoisaac.schoolmanager.controller;


import com.figueiredoisaac.schoolmanager.domain.dto.UserDto;
import com.figueiredoisaac.schoolmanager.domain.dto.output.UserDtoOutput;
import com.figueiredoisaac.schoolmanager.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/users")
@Tag(name = "Users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDtoOutput> findByUsername(
            @PathVariable("username") String username
    ) throws Exception {
        return new ResponseEntity(
                userService.findByUsername(username),
                HttpStatus.OK);
    }


    @PostMapping(value = "/register")
    public ResponseEntity<String> create(
            @Valid @RequestBody UserDto input
    ) throws Exception {
        userService.create(input);
        return new
                ResponseEntity<>(
                "Usuário Cadastrado com Sucesso",
                HttpStatus.CREATED
        );
    }
}
