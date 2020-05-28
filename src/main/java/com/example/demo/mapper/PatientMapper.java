package com.example.demo.mapper;

import com.example.demo.dto.DoctorDto;
import com.example.demo.entity.Patient;
import com.example.demo.dto.PatientDto;
import org.modelmapper.ModelMapper;

public class PatientMapper {



    public static PatientDto mapPatientToPatientDto(Patient patient){

        return new ModelMapper().map(patient, PatientDto.class);

    }

    public static Patient mapPatientDtoToPatient(Patient patient,PatientDto patientDto){

        patient.setFirstName(patientDto.getFirstName());
        //patient.setLastName(patientDto.getLastName());
        patient.setGender(patient.getGender());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setEmail(patientDto.getEmail());
        patient.setPesel(patientDto.getPesel());

        return patient;

    }



}
