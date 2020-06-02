package com.example.demo.serivceImpl;

import com.example.demo.dto.VisitDto;
import com.example.demo.entity.Patient;
import com.example.demo.entity.User;
import com.example.demo.mapper.VisitMapper;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VisitRepository;
import com.example.demo.service.VisitService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl implements VisitService {


    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;

    public VisitServiceImpl(VisitRepository visitRepository, PatientRepository patientRepository) {
        this.visitRepository = visitRepository;
        this.patientRepository = patientRepository;
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
                .filter(s -> s.getVisitDate().toLocalDate().compareTo(localDate)>=0)
                .map(VisitMapper::mapVisitToVisitDto)
                .collect(Collectors.toList());

        return visitDtoList;

    }

    @Override
    public List<VisitDto> getLoggedPatientVisits(Principal principal) {

        Patient patient = patientRepository.findByLogin(principal.getName());
        return patient.getVisits().stream()
                .map(VisitMapper::mapVisitToVisitDto)
                .collect(Collectors.toList());
    }
}
