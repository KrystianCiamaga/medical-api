package com.example.demo.entity.Doctor.service;

import com.example.demo.entity.Doctor.Doctor;
import com.example.demo.entity.Doctor.dto.DoctorDto;

import java.util.List;

public interface DoctorService {


    List<DoctorDto> findAll();
    DoctorDto findById(Long id);
    List<DoctorDto> findBySpecialization(String specialization);


}
