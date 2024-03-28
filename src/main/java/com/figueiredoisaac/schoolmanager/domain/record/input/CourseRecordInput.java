package com.figueiredoisaac.schoolmanager.domain.record.input;

import com.figueiredoisaac.schoolmanager.domain.enums.CourseStatus;
import com.figueiredoisaac.schoolmanager.domain.record.UserRecord;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record CourseRecordInput(
        @NotNull
        String name,
        @NotNull
        @Size(max = 20)
        @Pattern(regexp = "^[a-z](?:[a-z\\-]*[a-z])?$", message = "Code deve conter apenas caracteres minúsculos e '-' usado como separador ")
        String code,
        @NotNull
        String instructor,
        @NotNull
        String description


) {

}
