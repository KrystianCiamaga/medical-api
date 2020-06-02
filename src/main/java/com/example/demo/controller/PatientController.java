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

    @GetMapping
    public List<PatientDto> getAll(){
        return patientService.findAll();
    }

    @GetMapping("/{id}")
    public PatientDto getById(@PathVariable Long id){

        return patientService.findById(id);
    }

}
