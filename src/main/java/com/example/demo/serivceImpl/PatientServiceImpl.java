package com.example.demo.serivceImpl;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Medicine;
import com.example.demo.dto.MedicineDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.mapper.MedicineMapper;
import com.example.demo.entity.Patient;
import com.example.demo.dto.PatientDto;
import com.example.demo.mapper.PatientMapper;

import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PatientService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public PatientServiceImpl(PatientRepository patientRepository, MedicineRepository medicineRepository,
                              UserRepository userRepository, AddressRepository addressRepository) {
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public List<PatientDto> findAll() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::mapPatientToPatientDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto findById(Long id) {

        return PatientMapper.mapPatientToPatientDto(patientRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Could not find patient with id: "+id)));
    }


    @Override
    public void addMedicine(Long id, MedicineDto medicineDto) {

        Medicine medicine = new Medicine();
        MedicineMapper.mapMedicineDtoToMedicine(medicine, medicineDto);

        Patient patient = patientRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Could not find patient with id: "+id));

        patient.getMedicineList().add(medicine);

        medicineRepository.save(medicine);

    }

    @Override
    public List<MedicineDto> getLoggedPatientMedicines(Principal principal) {

        Patient patient = patientRepository.findByLogin(principal.getName());
        return patient.getMedicineList().stream()
                .map(MedicineMapper::mapMedicineToMedicineDto)
                .collect(Collectors.toList());

    }

    @Override
    public AddressDto findLoggedPatientAddres(Principal principal) {

        Patient patient = patientRepository.findByLogin(principal.getName());

        return AddressMapper.mapAddressToAddressDto(patient.getAddress());
    }

    @Override
    public void deleteMedicine(Long id, Long medicineId) {

        Patient patient = patientRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Could not find patient with id: "+id));

        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new NotFoundException("Could not find medicine with id:"+medicineId));

        patient.getMedicineList().remove(medicine);

        patientRepository.save(patient);


    }



}
