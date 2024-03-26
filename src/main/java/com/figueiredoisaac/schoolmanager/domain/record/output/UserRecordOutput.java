package com.figueiredoisaac.schoolmanager.domain.record.output;

import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record UserRecordOutput(

    String name,

    String email,

    UserRoles role

){
    
}

