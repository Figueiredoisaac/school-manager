package com.figueiredoisaac.schoolmanager.controller;

import com.figueiredoisaac.schoolmanager.domain.record.input.EnrollRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.output.EnrollRecordOutput;
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
    public ResponseEntity<EnrollRecordOutput> enrollment(
            @Valid @RequestBody EnrollRecordInput input
    ) throws Exception {

        return new ResponseEntity<>(
                enrollService.enrollment(input),
                HttpStatus.OK
        );
    }


}

