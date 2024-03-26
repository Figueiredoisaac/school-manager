package com.figueiredoisaac.schoolmanager.domain.record.output;

import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.record.UserRecord;

import java.time.LocalDateTime;

public record CourseRecordOutput(

    String name,

    String code,

    UserRecord instructor,

    String description,

    CourseStatus status,

    LocalDateTime createdAt,

    LocalDateTime disabledAt

){

}
