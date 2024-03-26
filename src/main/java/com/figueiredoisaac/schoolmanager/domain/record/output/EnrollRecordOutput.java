package com.figueiredoisaac.schoolmanager.domain.record.output;

import com.figueiredoisaac.schoolmanager.domain.record.CourseRecord;
import com.figueiredoisaac.schoolmanager.domain.record.UserRecord;

import java.time.LocalDateTime;

public record EnrollRecordOutput(
    Long id,
    UserRecord user,
    CourseRecord course,
    LocalDateTime enrolledAt

){

}
