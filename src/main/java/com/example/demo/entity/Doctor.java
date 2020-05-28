package com.example.demo.entity;


import com.example.demo.enums.DoctorSpecialization;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Doctor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Enumerated(EnumType.STRING)
    private DoctorSpecialization specialization;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST} )
    @JoinTable(name = "doctors_patients",
            joinColumns = @JoinColumn (name = "doctor_id"),inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patientList;

}

