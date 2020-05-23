package com.example.demo.controller;

import com.example.demo.entity.Address.dto.AddressDto;
import com.example.demo.entity.Medicine.dto.MedicineDto;
import com.example.demo.entity.Patient.dto.PatientDto;
import com.example.demo.entity.Patient.service.PatientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {


    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public List<PatientDto> getAll(){
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public PatientDto getById(@PathVariable Long id){
        return patientService.findById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    void deleteById(@PathVariable Long id){
        patientService.deleteById(id);
    }


    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @PostMapping("/add-medicine/{id}")
    public void addMedicine(@PathVariable Long id, @RequestBody MedicineDto medicineDto){

        patientService.addMedicine(id,medicineDto);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @DeleteMapping("/{patientId}/remove-medicine/{medicineId}")
    public void deleteMedicine(@PathVariable Long patientId, @PathVariable Long medicineId){

        patientService.deleteMedicine(patientId,medicineId);
    }


    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/all-medicines")
    public List<MedicineDto> getAllPatientMedicines(Principal principal){

        return patientService.getMedicines(principal);

    }

    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/my-address")
    public AddressDto getUserAddress(Principal principal){

        return patientService.getUserAddress(principal);
    }


}
