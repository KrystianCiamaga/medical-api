package com.example.demo.repository;

import com.example.demo.entity.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

    Patient findByLogin(String login);

}
