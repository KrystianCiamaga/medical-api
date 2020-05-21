package com.example.demo.controller;


import com.example.demo.entity.Doctor.dto.DoctorDto;
import com.example.demo.entity.Doctor.service.DoctorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
   public List<DoctorDto> getAll(){
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public DoctorDto getById(@PathVariable Long id){
        return  doctorService.findById(id);
    }

    @GetMapping("/specialization/{specialization}")
    public List<DoctorDto> getAllBySpecialization(@PathVariable("specialization") String specialization){

        return doctorService.findBySpecialization(specialization);
    }




}
