package com.example.demo.entity.Visit.dto;

import com.example.demo.entity.Patient.Patient;
import com.example.demo.entity.Patient.dto.PatientDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {


    private int doctorsOfficeNumber;
    private LocalDateTime visitDate;
    private PatientDto patientDto;


}
