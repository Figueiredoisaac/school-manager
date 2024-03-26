package com.figueiredoisaac.schoolmanager.domain.record.input;

import com.figueiredoisaac.schoolmanager.domain.CourseEntity;
import com.figueiredoisaac.schoolmanager.domain.UserEntity;
import jakarta.validation.constraints.NotBlank;

public record RatingRecordInput(

        Integer rating,
        @NotBlank
        String user,
        @NotBlank
        String course,
        @NotBlank
        String description
) {

}
