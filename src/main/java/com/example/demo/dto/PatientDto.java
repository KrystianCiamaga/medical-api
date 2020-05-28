package com.example.demo.dto;

import com.example.demo.enums.Gender;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDto {



    private String firstName;

    private String lastName;

    private Gender gender;

    private String email;

    private String phoneNumber;

    private Long pesel;







}
