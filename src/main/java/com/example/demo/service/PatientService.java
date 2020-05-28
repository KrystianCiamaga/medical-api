package com.example.demo.service;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.MedicineDto;
import com.example.demo.dto.PatientDto;

import java.security.Principal;
import java.util.List;


public interface PatientService {

    List<PatientDto> findAll();
    PatientDto findById(Long id);
    void addMedicine(Long id, MedicineDto medicineDto);
    List<MedicineDto> getLoggedPatientMedicines(Principal principal);
    void deleteMedicine(Long paientId, Long medicineId);





}
