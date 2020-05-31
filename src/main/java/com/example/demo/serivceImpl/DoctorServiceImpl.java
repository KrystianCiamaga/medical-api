package com.example.demo.serivceImpl;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Doctor;
import com.example.demo.dto.DoctorDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.mapper.DoctorMapper;
import com.example.demo.service.DoctorService;
import com.example.demo.enums.DoctorSpecialization;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
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

        return DoctorMapper.mapDoctorToDoctorDto(doctorRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Could not find doctor with id: "+id)));
    }

    @Override
    public List<DoctorDto> findBySpecialization(String specialization) {

        DoctorSpecialization doctorSpecialization = DoctorSpecialization.valueOf(specialization);

        List<Doctor> doctorList = doctorRepository.findAllBySpecialization(doctorSpecialization);

        return doctorList.stream()
                .map(DoctorMapper::mapDoctorToDoctorDto)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDto findLoggedDoctorAddress(Principal principal) {

        Doctor doctor = doctorRepository.findByLogin(principal.getName());

        return AddressMapper.mapAddressToAddressDto(doctor.getAddress());
    }
}
