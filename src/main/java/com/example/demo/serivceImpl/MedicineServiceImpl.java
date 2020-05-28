package com.example.demo.serivceImpl;
import com.example.demo.entity.Medicine;
import com.example.demo.dto.MedicineDto;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.MedicineMapper;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.service.MedicineService;
import org.springframework.stereotype.Service;

import java.util.List;
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

        return MedicineMapper.mapMedicineToMedicineDto(medicineRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Could not find medicine with id: "+id)));


    }

    @Override
    public void addMedicine(MedicineDto medicineDto) {

        Medicine medicine = MedicineMapper.mapMedicineDtoToMedicine(new Medicine(),medicineDto);
        medicineRepository.save(medicine);
    }

    @Override
    public void removeMedicineById(Long id) {

        if(medicineRepository.existsById(id))
            medicineRepository.deleteById(id);
        else
            throw new NotFoundException("Could not find medicine with id: "+id);

    }
}
