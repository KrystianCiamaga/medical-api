package com.example.demo.serivceImpl;
import com.example.demo.entity.Medicine;
import com.example.demo.dto.MedicineDto;
import com.example.demo.entity.Patient;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.MedicineMapper;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.MedicineService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final PatientRepository patientRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository, PatientRepository patientRepository) {
        this.medicineRepository = medicineRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<MedicineDto> findAll() {
        return medicineRepository.findAll().stream()
                .map(MedicineMapper::mapMedicineToMedicineDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicineDto findById(Long id) {

        return MedicineMapper.mapMedicineToMedicineDto(medicineRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Could not find medicine with id: "+id)));


    }

    @Override
    public void addMedicineToPatient(Long patientId,Long medicineid) {

       Optional<Medicine> medicine = Optional.ofNullable(medicineRepository
               .findById(medicineid).orElseThrow(() -> new NotFoundException("Could not find medicine with id: " + medicineid)));

        Patient patient = patientRepository.findById(patientId).
                orElseThrow(() -> new NotFoundException("Could not find patient with id: "+patientId));



        if(!patient.getMedicineList().contains(medicine.get().getId()==medicineid)){
            patient.getMedicineList().add(medicine.get());
            medicine.get().getPatients().add(patient);
            medicineRepository.saveAndFlush(medicine.get());
            patientRepository.saveAndFlush(patient);

        }else{
            throw new AlreadyExistsException("Patient with id "+patientId+" has this medicine.");
        }



    }

    @Override
    public void addMedicine(MedicineDto medicineDto) {

       Medicine medicine = MedicineMapper.mapMedicineDtoToMedicine(new Medicine(), medicineDto);

       if(medicineRepository.findByNameAndDose(medicineDto.getName(),medicineDto.getDose()) == null){

            medicineRepository.save(medicine);
        }else {
           throw new AlreadyExistsException("Medicine already exists in the database");
       }
    }

    @Override
    public void removeMedicineById(Long id) {

        if(medicineRepository.existsById(id))
            medicineRepository.deleteById(id);
        else
            throw new NotFoundException("Could not find medicine with id: "+id);

    }

    @Override
    public void removeMedicineFomPatient(Long medicineId,Long patientId) {


        Patient patient = patientRepository.findById(patientId).
                orElseThrow(() -> new NotFoundException("Could not find patient with id: "+patientId));

        Medicine medicine = medicineRepository
                .findById(medicineId).orElseThrow(() -> new NotFoundException("Could not find medicine with id: " + medicineId));


        if(patient.getMedicineList().contains(medicine)){

            patient.getMedicineList().remove(medicine);
            patientRepository.saveAndFlush(patient);


        }

        else
            throw new NotFoundException("Could not find medicine with id: "+medicineId);

    }

    @Override
    public List<MedicineDto> getLoggedPatientMedicines(Principal principal) {

        Patient patient = patientRepository.findByLogin(principal.getName());
        return patient.getMedicineList().stream()
                .map(MedicineMapper::mapMedicineToMedicineDto)
                .collect(Collectors.toList());
    }
}
