package com.figueiredoisaac.schoolmanager.controller;

import com.figueiredoisaac.schoolmanager.domain.dto.RatingDto;
import com.figueiredoisaac.schoolmanager.domain.dto.RatingDtoNpc;
import com.figueiredoisaac.schoolmanager.service.RatingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rating")
@Tag(name = "Rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping(value = "/new")
    public ResponseEntity<String> rating(
            @Valid @RequestBody RatingDto input
    ) throws Exception {
        ratingService.rating(input);
        return new ResponseEntity<>("Avaliação Enviada!"
                ,
                HttpStatus.CREATED
        );

    }

    @GetMapping(value = "/npsReport")
    public ResponseEntity<Page<RatingDtoNpc>> npsReport(
            @Valid @RequestParam("page") Integer page
    ) throws Exception {
        return new ResponseEntity<>(
                ratingService.npcReport(page),
                HttpStatus.OK
        );
    }
}