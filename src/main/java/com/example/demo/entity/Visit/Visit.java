package com.example.demo.entity.Visit;


import com.example.demo.entity.Patient.Patient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
