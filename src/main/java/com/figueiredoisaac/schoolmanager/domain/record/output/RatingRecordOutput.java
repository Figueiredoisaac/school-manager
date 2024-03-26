package com.figueiredoisaac.schoolmanager.domain.record.output;

import com.figueiredoisaac.schoolmanager.domain.CourseEntity;
import com.figueiredoisaac.schoolmanager.domain.UserEntity;

public record RatingRecordOutput(

        Long id,
        Integer rating,
        UserEntity user,
        CourseEntity course,
        String description
) {

}
