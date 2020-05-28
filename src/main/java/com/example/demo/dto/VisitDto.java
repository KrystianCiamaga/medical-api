package com.example.demo.dto;

import com.example.demo.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitDto {


    private int doctorsOfficeNumber;
    private LocalDateTime visitDate;
    private PatientDto patientDto;


}
