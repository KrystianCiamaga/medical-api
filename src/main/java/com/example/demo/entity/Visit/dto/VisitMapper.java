package com.example.demo.entity.Visit.dto;

import com.example.demo.entity.Patient.dto.PatientMapper;
import com.example.demo.entity.Visit.Visit;

public class VisitMapper {



    public static VisitDto mapVisitToVisitDto(Visit visit){

        VisitDto visitDto = new VisitDto();

        if(visit !=null){

            visitDto.setPatientDto(PatientMapper.mapPatientToPatientDto(visit.getPatient()));
            visitDto.setVisitDate(visit.getVisitDate());
            visitDto.setDoctorsOfficeNumber(visit.getDoctorsOfficeNumber());

        }
        return visitDto;
    }

/*
    public static Visit mapVisitDtoToVisit(Visit visit,VisitDto visitDto){


        visit.setPatient(PatientMapper.mapPatientDtoToPatient());


    }*/


}
