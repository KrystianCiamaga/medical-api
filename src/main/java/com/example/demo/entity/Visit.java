package com.example.demo.entity;


import com.example.demo.entity.Patient;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data

@RequiredArgsConstructor
public class Visit {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "doctors_office_number")
    private int doctorsOfficeNumber;

    @Column(name = "visit_date")
    private LocalDateTime visitDate;

    @ManyToOne()
    private Patient patient;


}
