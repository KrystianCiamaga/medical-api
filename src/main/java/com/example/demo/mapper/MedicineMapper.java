package com.example.demo.mapper;

import com.example.demo.dto.MedicineDto;
import com.example.demo.entity.Medicine;
import org.modelmapper.ModelMapper;

public class MedicineMapper {


    public static MedicineDto mapMedicineToMedicineDto(Medicine medicine){

        ModelMapper modelMapper = new ModelMapper();


        if(medicine != null) {
            return modelMapper.map(medicine,MedicineDto.class);

        }

        return null;
    }


    public static Medicine mapMedicineDtoToMedicine(Medicine medicine,MedicineDto medicineDto){

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(medicineDto,Medicine.class);

    }


}
