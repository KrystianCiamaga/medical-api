package com.example.demo.repository;

import com.example.demo.dto.MedicineDto;
import com.example.demo.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {


    Medicine findByNameAndDose(String name, int dose);

}
