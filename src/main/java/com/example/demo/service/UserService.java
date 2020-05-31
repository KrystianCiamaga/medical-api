package com.example.demo.service;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserReadDto;
import com.example.demo.entity.User;

import java.security.Principal;


public interface UserService {



    void saveUser(UserCreateDto user) throws Exception;
    void checkToken(String token);





}
