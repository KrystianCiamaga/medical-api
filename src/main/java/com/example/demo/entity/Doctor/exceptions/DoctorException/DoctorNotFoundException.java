package com.example.demo.entity.Doctor.exceptions.DoctorException;


public class DoctorNotFoundException extends RuntimeException {


    public DoctorNotFoundException(Long id) {
        super("Could not find doctor with "+id+" id");
    }
}
