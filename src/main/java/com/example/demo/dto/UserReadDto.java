package com.example.demo.dto;

import com.example.demo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserReadDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Long pesel;

    private AddressDto addressDto;






}
