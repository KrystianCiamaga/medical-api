package com.example.demo.entity.Medicine.exceptions.MedicineException;

public class MedicineNoutFoundException extends RuntimeException {


    public MedicineNoutFoundException(Long id) {

        super("Cant find any medicine with id "+id);

    }
}
