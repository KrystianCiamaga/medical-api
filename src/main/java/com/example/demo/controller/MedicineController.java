package com.example.demo.controller;


import com.example.demo.dto.MedicineDto;
import com.example.demo.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @GetMapping
    public List<MedicineDto> getAll(){

        return medicineService.findAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @GetMapping("/{id}")
    public MedicineDto getById(@PathVariable Long id){

        return medicineService.findById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @PostMapping
    public void addMedicine(@RequestBody MedicineDto medicineDto){

        medicineService.addMedicine(medicineDto);
    }

    @DeleteMapping("/remove/{id}")
    public void removeMedicineById(@PathVariable Long id){

        medicineService.removeMedicineById(id);

    }


}
