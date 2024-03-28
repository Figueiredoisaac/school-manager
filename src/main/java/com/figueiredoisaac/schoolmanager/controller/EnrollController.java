package com.figueiredoisaac.schoolmanager.controller;

import com.figueiredoisaac.schoolmanager.domain.dto.EnrollDto;
import com.figueiredoisaac.schoolmanager.service.EnrollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/enroll")
public class EnrollController {

    @Autowired
    private EnrollService enrollService;


    @PostMapping(value = "/new")
    public ResponseEntity<String> enrollment(
            @Valid @RequestBody EnrollDto input
    ) throws Exception {
            enrollService.enrollment(input);
        return new ResponseEntity<>("Matriculado com sucesso!"
                ,
                HttpStatus.OK
        );
    }


}

