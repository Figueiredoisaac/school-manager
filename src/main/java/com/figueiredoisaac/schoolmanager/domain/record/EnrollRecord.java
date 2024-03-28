package com.figueiredoisaac.schoolmanager.domain.record;

import java.time.LocalDateTime;

public record EnrollRecord (
    Long id,
    UserRecord user,
    CourseRecord course,
    LocalDateTime enrolledAt

){

}
