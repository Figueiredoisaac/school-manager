package com.figueiredoisaac.schoolmanager.domain.dto.output;

import com.figueiredoisaac.schoolmanager.domain.enums.UserRoles;

public class UserDtoOutput {

    private String name;

    private String email;

    private UserRoles role;

    public UserDtoOutput() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

}

