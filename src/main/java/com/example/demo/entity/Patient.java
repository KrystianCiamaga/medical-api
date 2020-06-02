package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@RequiredArgsConstructor
public class Patient extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "patients_visits",
            joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "vistit_id"))
    private List<Visit> visits;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinTable(name = "patients_medicines",
            joinColumns = @JoinColumn(name = "patient_id"), inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private List<Medicine> medicineList;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH} )
    private Doctor doctor;



}
