package com.figueiredoisaac.schoolmanager.controller;

import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.record.input.CourseRecordInput;
import com.figueiredoisaac.schoolmanager.domain.record.output.CourseRecordOutput;
import com.figueiredoisaac.schoolmanager.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus.ATIVO;
import static com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus.INATIVO;

@RestController
@RequestMapping("/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/search")
    public ResponseEntity<Page<CourseRecordOutput>> findAllByStatus(
            @RequestParam(name = "status", required = false) CourseStatus status,
            @RequestParam(name = "p") Integer page
    ) throws Exception {
        if (status == ATIVO || status == INATIVO) {
            return new ResponseEntity<>(courseService.findAllByStatus(status, PageRequest.of(page - 1, 10)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(courseService.findAll(PageRequest.of(page - 1, 10)), HttpStatus.OK);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> create(
            @Valid @RequestBody CourseRecordInput input
    ) throws Exception {
        courseService.create(input);

        return new ResponseEntity<>(
                "Curso cadastrado com Sucesso!",
                HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{code}/{active}")
    public ResponseEntity<CourseStatus> findAllByStatus(
            @PathVariable("code") String code,
            @PathVariable("active") Boolean active
    ) throws Exception {
        return new ResponseEntity<>(
                courseService.changeStatus(code, active),
                HttpStatus.OK
        );
    }

}




