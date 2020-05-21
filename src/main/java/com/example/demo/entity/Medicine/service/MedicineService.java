package com.example.demo.entity.Medicine.service;

import com.example.demo.entity.Medicine.Medicine;
import com.example.demo.entity.Medicine.dto.MedicineDto;

import java.util.List;

public interface MedicineService {


    List<MedicineDto> findAll();
    MedicineDto findById(Long id);
    void addMedicine(MedicineDto medicineDto);


}
