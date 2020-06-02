package com.example.demo.controller;


import com.example.demo.dto.MedicineDto;
import com.example.demo.entity.Patient;
import com.example.demo.service.MedicineService;
import com.example.demo.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private final MedicineService medicineService;
    private final PatientService patientService;


    public MedicineController(MedicineService medicineService, PatientService patientService) {
        this.medicineService = medicineService;
        this.patientService = patientService;
    }



    @GetMapping
    public List<MedicineDto> getAll(){

        return medicineService.findAll();
    }


    @GetMapping("/{id}")
    public MedicineDto getById(@PathVariable Long id){

        return medicineService.findById(id);
    }

    @GetMapping("/showMedicines")
    public List<MedicineDto> getAllLoggedPatientMedicines(Principal principal){

        return medicineService.getLoggedPatientMedicines(principal);
    }

   // @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @PostMapping("/")
    public void addMedicineToPatient (@RequestParam Long patientId, @RequestParam Long medicineId){

        medicineService.addMedicineToPatient(patientId,medicineId);
    }


    @PostMapping
    public void addMedicine(@RequestBody MedicineDto medicineDto){

        medicineService.addMedicine(medicineDto);
    }


    @DeleteMapping("/remove/patient/{patientId}/medicine/{medicineId}")
    public void removeMedicineFromPatient (@RequestParam Long patientId, @RequestParam Long medicineId){

        medicineService.removeMedicineFomPatient(medicineId,patientId);
    }

    @DeleteMapping("/{id}")
    public void removeMedicineById(@PathVariable Long id){

        medicineService.removeMedicineById(id);

    }


}
