package com.figueiredoisaac.schoolmanager.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus
public class BadRequestException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException(String ex){
        super(ex);
    }

}
