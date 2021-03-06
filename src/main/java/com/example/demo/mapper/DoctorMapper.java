package com.example.demo.mapper;

import com.example.demo.dto.DoctorDto;
import com.example.demo.entity.Doctor;
import org.modelmapper.ModelMapper;

public class DoctorMapper {


    public static DoctorDto mapDoctorToDoctorDto(Doctor doctor){

        ModelMapper modelMapper = new ModelMapper();


        return modelMapper.map(doctor,DoctorDto.class);


      /*  DoctorDto doctorDto = new DoctorDto();

        if(doctor !=null){

            doctorDto.setFirstName(doctor.getFirstName());
            doctorDto.setLastName(doctor.getLastName());
            doctorDto.setEmail(doctor.getEmail());
            doctorDto.setGender(doctor.getGender());
            doctorDto.setPhoneNumber(doctor.getPhoneNumber());
            doctorDto.setSpecialization(doctor.getSpecialization());
        }
        return doctorDto;
*/
    }

    public static Doctor mapDoctorDtoToDoctor(Doctor doctor,DoctorDto doctorDto){

        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setPhoneNumber(doctorDto.getPhoneNumber());
        doctor.setGender(doctorDto.getGender());

        return doctor;

    }

}
