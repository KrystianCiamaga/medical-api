package com.example.demo.mapper;

import com.example.demo.dto.MedicineDto;
import com.example.demo.entity.Medicine;

public class MedicineMapper {


    public static MedicineDto mapMedicineToMedicineDto(Medicine medicine){


        MedicineDto medicineDto = new MedicineDto();

        if(medicine != null) {
            medicineDto.setName(medicine.getName());
            medicineDto.setDose(medicine.getDose());
        }

        return medicineDto;
    }


    public static Medicine mapMedicineDtoToMedicine(Medicine medicine,MedicineDto medicineDto){


        medicine.setName(medicineDto.getName());
        medicine.setDose(medicineDto.getDose());

        return medicine;

    }


}
