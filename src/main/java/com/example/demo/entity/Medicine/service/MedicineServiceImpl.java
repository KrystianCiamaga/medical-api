package com.example.demo.entity.Medicine.service;

import com.example.demo.entity.Medicine.Medicine;
import com.example.demo.entity.Medicine.dto.MedicineDto;
import com.example.demo.entity.Medicine.dto.MedicineMapper;
import com.example.demo.entity.Medicine.exceptions.MedicineException.MedicineNoutFoundException;
import com.example.demo.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override
    public List<MedicineDto> findAll() {
        return medicineRepository.findAll().stream()
                .map(MedicineMapper::mapMedicineToMedicineDto)
                .collect(Collectors.toList());
    }

    @Override
    public MedicineDto findById(Long id) {

        Optional<Medicine> medicine = Optional.ofNullable(medicineRepository.findById(id).orElseThrow(() -> new MedicineNoutFoundException(id)));

        return MedicineMapper.mapMedicineToMedicineDto(medicine.get());
    }

    @Override
    public void addMedicine(MedicineDto medicineDto) {

        Medicine medicine = MedicineMapper.mapMedicineDtoToMedicine(new Medicine(),medicineDto);
        medicineRepository.save(medicine);
    }
}
