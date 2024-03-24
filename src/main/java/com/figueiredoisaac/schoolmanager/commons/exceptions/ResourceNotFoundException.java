package com.figueiredoisaac.schoolmanager.commons.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus
public class ResourceNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String ex){
        super(ex);
    }

}
