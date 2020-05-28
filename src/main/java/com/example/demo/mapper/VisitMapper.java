package com.example.demo.mapper;

import com.example.demo.dto.VisitDto;
import com.example.demo.entity.Visit;
import org.modelmapper.ModelMapper;

public class VisitMapper {



    public static VisitDto mapVisitToVisitDto(Visit visit){



        if(visit !=null){

           return new ModelMapper().map(visit,VisitDto.class);

        }
        return null;
    }

/*
    public static Visit mapVisitDtoToVisit(Visit visit,VisitDto visitDto){


        visit.setPatient(PatientMapper.mapPatientDtoToPatient());


    }*/


}
