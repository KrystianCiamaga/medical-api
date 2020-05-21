package com.example.demo.entity.Patient;

import com.example.demo.entity.Address.Address;
import com.example.demo.entity.Doctor.Doctor;
import com.example.demo.entity.Medicine.Medicine;
import com.example.demo.entity.User.User;
import com.example.demo.entity.Visit.Visit;
import com.example.demo.enums.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Enumerated
    private Gender gender;

    private Long pesel;

    @ManyToOne
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "patients_visits",
            joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "vistit_id"))
    private List<Visit> visits;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "patients_medicines",
            joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private List<Medicine> medicineList;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.REMOVE} )
    private Doctor doctor;



}
