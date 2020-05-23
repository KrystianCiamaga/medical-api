package com.example.demo.controller;

import com.example.demo.entity.Visit.dto.VisitDto;
import com.example.demo.entity.Visit.service.VisitService;
import org.springframework.cglib.core.Local;
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


    @GetMapping("/date?date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    public List<VisitDto> getVisitsByDate(
                                          @RequestParam(name = "date")
                                          @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                  LocalDate date){

       return visitService.findVisitsByDate(date);

    }

}
