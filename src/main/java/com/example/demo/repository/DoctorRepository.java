package com.example.demo.repository;

import com.example.demo.entity.Doctor.Doctor;
import com.example.demo.enums.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends  UserBaseRepository<Doctor> {


    List<Doctor> findAllBySpecialization(DoctorSpecialization specialization);


}
