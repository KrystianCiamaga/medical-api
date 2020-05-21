package com.example.demo.entity.Patient.dto;

import com.example.demo.entity.Patient.Patient;
import com.example.demo.entity.Patient.dto.PatientDto;

public class PatientMapper {



    public static PatientDto mapPatientToPatientDto(Patient patient){

        PatientDto patientDto = new PatientDto();

        if(patient.getId() != null) {
            patientDto.setFirstName(patient.getFirstName());
            patientDto.setLastName(patient.getLastName());
            patientDto.setEmail(patient.getEmail());
            patientDto.setGender(patient.getGender());
            patientDto.setPesel(patient.getPesel());
            patientDto.setPhoneNumber(patient.getPhoneNumber());
        }


        return patientDto;

    }

    public static Patient mapPatientDtoToPatient(Patient patient,PatientDto patientDto){

        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setGender(patient.getGender());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setEmail(patientDto.getEmail());
        patient.setPesel(patientDto.getPesel());

        return patient;

    }



}
