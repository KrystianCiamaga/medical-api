package com.example.demo.entity.Doctor.exceptions.DoctorException;


import org.hibernate.annotations.NotFound;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DoctorNotFoundAdvice {


    @ResponseBody
    @ExceptionHandler(DoctorNotFoundException.class)
    @NotFound
    public String DoctortNotFoundHandler(DoctorNotFoundException ex) {

         return ex.getMessage();

    }
}
