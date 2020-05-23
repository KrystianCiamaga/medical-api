package com.example.demo.entity.Patient.exceptions.PatientNotFound;

public class PatientNotFoundException extends RuntimeException {


    public PatientNotFoundException(long id) {

        super("Could not find patient with "+id+" id");

    }

    public PatientNotFoundException() {

        super("Could not find patient") ;
    }
}
