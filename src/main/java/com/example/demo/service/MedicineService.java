package com.example.demo.service;

import com.example.demo.dto.MedicineDto;
import com.example.demo.entity.Medicine;

import java.security.Principal;
import java.util.List;

public interface MedicineService {


    List<MedicineDto> findAll();
    MedicineDto findById(Long medicineid);
    void addMedicine(MedicineDto medicineDto);
    void removeMedicineById(Long medicineId);
    void removeMedicineFomPatient(Long medicineId,Long patientId);
    void addMedicineToPatient(Long medicineId,Long patientId);
    List<MedicineDto> getLoggedPatientMedicines(Principal principal);





}
