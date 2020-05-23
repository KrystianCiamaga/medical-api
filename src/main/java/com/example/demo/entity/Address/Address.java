package com.example.demo.entity.Address;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;

    @Column(name = "zipp_code")
    private String zippCode;

    @Column(name = "house_number")
    private String houseNumber;






}
