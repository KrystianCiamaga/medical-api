package com.example.demo.enums;

public enum DoctorSpecialization {

    DERMATOLOGIST("Dermatologist"),
    ENDOCRINOLOGIST("Endocrinologist"),
    ANESTESIOLOGITS("Anesthesiologist"),
    ALLERGIST("Allergist"),
    FAMILY_PHYSYCIAN("Family Physician");


    private String value;

    DoctorSpecialization(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
