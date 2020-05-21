package com.example.demo.controller;

import com.example.demo.entity.Visit.dto.VisitDto;
import com.example.demo.entity.Visit.service.VisitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {


    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }


    @GetMapping
    public List<VisitDto> getAllVisits(){
        return visitService.findAll();
    }


}
