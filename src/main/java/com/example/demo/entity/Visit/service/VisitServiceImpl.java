package com.example.demo.entity.Visit.service;

import com.example.demo.entity.Visit.Visit;
import com.example.demo.entity.Visit.dto.VisitDto;
import com.example.demo.entity.Visit.dto.VisitMapper;
import com.example.demo.repository.VisitRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {


    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public List<VisitDto> findAll() {

        return visitRepository.findAll().stream()
                .map(VisitMapper::mapVisitToVisitDto)
                .collect(Collectors.toList());
    }

    @Override
    public VisitDto findById(Long id) {
        return null;
    }

    @Override
    public List<VisitDto> findVisitsByDate(LocalDate localDate) {

        List<VisitDto> visitDtoList = visitRepository.findAll().stream()
                .filter(s -> s.getVisitDate().getDayOfYear()==localDate.getDayOfYear())
                .map(VisitMapper::mapVisitToVisitDto)
                .collect(Collectors.toList());

        return visitDtoList;

    }
}
