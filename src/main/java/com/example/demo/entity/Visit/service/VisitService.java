package com.example.demo.entity.Visit.service;

import com.example.demo.entity.Visit.dto.VisitDto;

import java.time.LocalDate;
import java.util.List;

public interface VisitService {


    List<VisitDto> findAll();
    VisitDto findById(Long id);
    List<VisitDto> findVisitsByDate(LocalDate localDate);

}
