package com.example.demo.exception;

import com.example.demo.entity.Medicine;

import java.util.function.Consumer;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
