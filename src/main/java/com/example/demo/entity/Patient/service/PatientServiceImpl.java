package com.example.demo.entity.Patient.service;

import com.example.demo.entity.Address.Address;
import com.example.demo.entity.Address.dto.AddressDto;
import com.example.demo.entity.Address.dto.AddressMapper;
import com.example.demo.entity.Medicine.Medicine;
import com.example.demo.entity.Medicine.dto.MedicineDto;
import com.example.demo.entity.Medicine.dto.MedicineMapper;
import com.example.demo.entity.Patient.Patient;
import com.example.demo.entity.Patient.dto.PatientDto;
import com.example.demo.entity.Patient.dto.PatientMapper;
import com.example.demo.entity.Patient.exceptions.PatientNotFound.PatientNotFoundException;
import com.example.demo.repository.AddressRepository;
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

    @Override
    public void deleteMedicine(Long paientId, Long medicineId) {

        Optional<Patient> patient = Optional.ofNullable(patientRepository.
                findById(paientId).orElseThrow(() -> new PatientNotFoundException(paientId)));

        Optional<Medicine> medicine = medicineRepository.findById(medicineId);
        patient.get().getMedicineList().remove(medicine.get());

        patientRepository.save(patient.get());


    }

    @Override
    public AddressDto getUserAddress(Principal principal) {

        Patient patient = patientRepository.findByLogin(principal.getName());
        Address address = patient.getAddress();

        return AddressMapper.mapAddressToAddressDto(address);
    }


}
