package com.example.demo.service;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.DoctorDto;

import java.security.Principal;
import java.util.List;

public interface DoctorService {


    List<DoctorDto> findAll();
    DoctorDto findById(Long id);
    List<DoctorDto> findBySpecialization(String specialization);
    AddressDto findLoggedDoctorAddress(Principal principal);


}
