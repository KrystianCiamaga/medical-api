package com.example.demo.mapper;

import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserReadDto;
import com.example.demo.entity.Doctor;
import com.example.demo.entity.Patient;
import com.example.demo.entity.User;
import com.sun.xml.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    private static  PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        UserMapper.passwordEncoder=passwordEncoder;
    }

    public static User mapUserCreateDtoToUser(UserCreateDto userCreateDto){



        String role = userCreateDto.getRole();

        switch (role){

            case "ROLE_DOCTOR":{
                Doctor doctor = new Doctor();
                doctor.setLogin(userCreateDto.getLogin());
                doctor.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
                doctor.setRole(userCreateDto.getRole());
                doctor.setEmail(userCreateDto.getEmail());
                return doctor;

            }
            case "ROLE_PATIENT":{
                Patient patient = new Patient();
                patient.setLogin(userCreateDto.getLogin());
                patient.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
                patient.setRole(userCreateDto.getRole());
                patient.setEmail(userCreateDto.getEmail());
                return patient;
            }


        }

        return null;
    }

    public static UserReadDto mapUserToUserReadDto(User user){

        UserReadDto userReadDto = new UserReadDto();


        userReadDto.setFirstName(user.getFirstName());
        userReadDto.setLastName(user.getLastName());
        userReadDto.setEmail(user.getEmail());
        userReadDto.setPesel(user.getPesel());
        userReadDto.setPhoneNumber(user.getPhoneNumber());
        userReadDto.setAddressDto(AddressMapper.mapAddressToAddressDto(user.getAddress()));
        return userReadDto;


    }






}
