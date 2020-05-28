package com.example.demo.controller;


import com.example.demo.dto.DoctorDto;
import com.example.demo.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
   public List<DoctorDto> getAll(){
        return doctorService.findAll();
    }


    @GetMapping("/{id}")
    public DoctorDto getById(@PathVariable Long id){
        return  doctorService.findById(id);
    }


    @GetMapping("/specialization")
    public List<DoctorDto> getAllBySpecialization(@RequestParam("value") String specialization){

        return doctorService.findBySpecialization(specialization);
    }




}
