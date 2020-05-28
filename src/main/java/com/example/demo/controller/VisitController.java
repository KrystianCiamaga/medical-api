package com.example.demo.controller;

import com.example.demo.dto.VisitDto;
import com.example.demo.service.VisitService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {


    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }




    @PreAuthorize("hasAnyRole('ROLE_DOCTOR','ROLE_ADMIN')")
    @GetMapping("/all")
    public List<VisitDto> getAllVisits(){
        return visitService.findAll();
    }


    @GetMapping("/date")
    public List<VisitDto> getvisitsByDate(@RequestParam("value")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate date){

        return visitService.findVisitsByDate(date);

    }



}
