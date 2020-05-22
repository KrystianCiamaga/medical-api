package com.example.demo.entity.Patient.service;

import com.example.demo.entity.Medicine.Medicine;
import com.example.demo.entity.Medicine.dto.MedicineDto;
import com.example.demo.entity.Patient.Patient;
import com.example.demo.entity.Patient.dto.PatientDto;

import java.security.Principal;
import java.util.List;


public interface PatientService {

    List<PatientDto> findAll();
    PatientDto findById(Long id);
    void deleteById(Long id);
    void addMedicine(Long id, MedicineDto medicineDto);
    List<MedicineDto> getMedicines(Principal principal);





}
