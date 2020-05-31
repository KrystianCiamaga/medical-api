package com.example.demo.controller;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.MedicineDto;
import com.example.demo.dto.PatientDto;
import com.example.demo.service.PatientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
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

    @GetMapping("/accounts/medicines")
    public List<MedicineDto> getAllLoggedPatientMedicines(Principal principal){

        return patientService.getLoggedPatientMedicines(principal);

    }

    @GetMapping("/accounts/address")
    public AddressDto getLoggedPatientAddress(Principal principal){
        return patientService.findLoggedPatientAddres(principal);
    }




}
