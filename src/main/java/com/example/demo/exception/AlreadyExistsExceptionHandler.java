package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class AlreadyExistsExceptionHandler {



    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<Object> alreadyExistsExceptionHandler(AlreadyExistsException ex){



        ApiException apiException = new ApiException(
                ex.getMessage(),
                HttpStatus.CREATED,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiException,apiException.getHttpStatus());

    }

}
