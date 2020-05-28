package com.example.demo.service;

import com.example.demo.dto.MedicineDto;

import java.util.List;

public interface MedicineService {


    List<MedicineDto> findAll();
    MedicineDto findById(Long id);
    void addMedicine(MedicineDto medicineDto);
    void removeMedicineById(Long id);


}
