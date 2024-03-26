package com.figueiredoisaac.schoolmanager.domain.record.input;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record UserRecordInput(

        String name,
        String username,
        String email,
        String password,
        @Enumerated(EnumType.STRING)
        UserRoles role,
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        LocalDateTime createdAt
) {
}

