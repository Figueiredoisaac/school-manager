package com.figueiredoisaac.schoolmanager.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus
public class NotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException(String ex){
        super(ex);
    }

}
