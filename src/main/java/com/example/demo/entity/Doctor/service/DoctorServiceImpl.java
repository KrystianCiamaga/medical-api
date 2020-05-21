package com.example.demo.entity.Doctor.service;

import com.example.demo.entity.Doctor.Doctor;
import com.example.demo.entity.Doctor.dto.DoctorDto;
import com.example.demo.entity.Doctor.dto.DoctorMapper;
import com.example.demo.entity.Doctor.exceptions.DoctorException.DoctorNotFoundException;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }



    @Override
    public List<DoctorDto> findAll() {

        return doctorRepository.findAll().stream()
                .map(DoctorMapper::mapDoctorToDoctorDto)
                .collect(Collectors.toList());
    }



    @Override
    public DoctorDto findById(Long id) {

        Optional<Doctor> doctor = Optional.ofNullable(doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFoundException(id)));

        return DoctorMapper.mapDoctorToDoctorDto(doctor.get());
    }

    @Override
    public List<DoctorDto> findBySpecialization(String specialization) {

        List<Doctor> doctorList = doctorRepository.findAllBySpecialization(specialization);

        return doctorList.stream()
                .map(DoctorMapper::mapDoctorToDoctorDto)
                .collect(Collectors.toList());
    }
}
