package com.figueiredoisaac.schoolmanager.domain.record;

import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record UserRecord (

    Long id,
    String name,

    String username,

    String email,

    String password,

    UserRoles role,

    LocalDateTime createdAt
){


}

