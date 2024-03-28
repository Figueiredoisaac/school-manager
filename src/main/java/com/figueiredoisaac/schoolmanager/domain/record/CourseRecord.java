package com.figueiredoisaac.schoolmanager.domain.record;

import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

public record CourseRecord (

    Long id,

    String name,

    String code,

    UserRecord instructor,

    String description,

    CourseStatus status,

    LocalDateTime createdAt,

    LocalDateTime disabledAt

){

}
