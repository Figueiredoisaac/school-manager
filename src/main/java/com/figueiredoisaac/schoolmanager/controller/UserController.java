package com.figueiredoisaac.schoolmanager.controller;


import com.figueiredoisaac.schoolmanager.domain.record.UserRecord;
import com.figueiredoisaac.schoolmanager.domain.record.input.UserRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.output.UserRecordOutput;
import com.figueiredoisaac.schoolmanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/{username}")
    public ResponseEntity<UserRecordOutput> findByUsername(
            @PathVariable("username") String username
    ) throws Exception
    {
        return new ResponseEntity(
                userService.findByUsername(username),
                HttpStatus.OK);
    }


    @PostMapping(value = "/register")
    public ResponseEntity<String> create(
            @Valid @RequestBody UserRecordInput input
    ) throws Exception {
        userService.create(input);
        return new
                ResponseEntity<>(
                        "Usuário Cadastrado com Sucesso",
                        HttpStatus.CREATED
        );
    }
}
