package com.figueiredoisaac.schoolmanager.domain.record.input;

import com.figueiredoisaac.schoolmanager.domain.record.CourseRecord;
import com.figueiredoisaac.schoolmanager.domain.record.UserRecord;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EnrollRecordInput(
        @NotNull
        String user,
        @NotNull
        String course

) {

}
