package com.example.demo.entity.Doctor;


import com.example.demo.entity.Patient.Patient;
import com.example.demo.entity.User.User;
import com.example.demo.enums.DoctorSpecialization;
import com.example.demo.enums.Gender;
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

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;


    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private DoctorSpecialization specialization;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.PERSIST} )
    @JoinTable(name = "doctors_patients",
            joinColumns = @JoinColumn (name = "doctor_id"),inverseJoinColumns = @JoinColumn(name = "patient_id"))
    private List<Patient> patientList;

}

