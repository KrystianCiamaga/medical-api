package com.example.demo.service;

import com.example.demo.dto.VisitDto;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public interface VisitService {


    List<VisitDto> findAll();
    VisitDto findById(Long id);
    List<VisitDto> findVisitsByDate(LocalDate localDate);
    List<VisitDto> getLoggedPatientVisits(Principal principal);

}
