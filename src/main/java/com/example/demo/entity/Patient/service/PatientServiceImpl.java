package com.example.demo.entity.Patient.service;

import com.example.demo.entity.Medicine.Medicine;
import com.example.demo.entity.Medicine.dto.MedicineDto;
import com.example.demo.entity.Medicine.dto.MedicineMapper;
import com.example.demo.entity.Patient.Patient;
import com.example.demo.entity.Patient.dto.PatientDto;
import com.example.demo.entity.Patient.dto.PatientMapper;
import com.example.demo.entity.Patient.exceptions.PatientNotFound.PatientNotFoundException;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;
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

    public PatientServiceImpl(PatientRepository patientRepository, MedicineRepository medicineRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.medicineRepository = medicineRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<PatientDto> findAll() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::mapPatientToPatientDto)
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto findById(Long id) {

        Optional<Patient> patient = patientRepository.findById(id);

        return PatientMapper.mapPatientToPatientDto(patient.orElseThrow(() -> new PatientNotFoundException(id)));
    }

    @Override
    public void deleteById(Long id) {

        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        } else {
            throw new PatientNotFoundException(id);
        }

    }

    @Override
    public void addMedicine(Long id, MedicineDto medicineDto) {

        Medicine medicine = new Medicine();
        MedicineMapper.mapMedicineDtoToMedicine(medicine, medicineDto);

        Optional<Patient> patient = Optional.ofNullable(patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException(id)));

        patient.get().getMedicineList().add(medicine);

        medicineRepository.save(medicine);

    }

    @Override
    public List<MedicineDto> getMedicines(Principal principal) {

        Patient patient = patientRepository.findByLogin(principal.getName());
        return patient.getMedicineList().stream()
                .map(MedicineMapper::mapMedicineToMedicineDto)
                .collect(Collectors.toList());

    }




}
