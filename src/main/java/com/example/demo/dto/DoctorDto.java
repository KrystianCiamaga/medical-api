package com.example.demo.dto;

import com.example.demo.enums.DoctorSpecialization;
import com.example.demo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {

    private String firstName;

    private String lastName;

    private Gender gender;

    private String email;

    private String phoneNumber;

    private DoctorSpecialization specialization;

}
