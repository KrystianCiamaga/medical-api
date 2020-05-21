package com.example.demo.enums;

public enum Role {

    PATIENT("ROLE_PATIENT"),
    ADMIN("ROLE_ADMIN"),
    DOCTOR("ROLE_DOCTOR");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

