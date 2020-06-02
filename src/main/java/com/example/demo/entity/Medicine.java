package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data

@RequiredArgsConstructor
public class Medicine {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int dose;

    @ManyToMany(mappedBy = "medicineList")
    private List<Patient> patients;


}