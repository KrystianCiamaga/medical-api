package com.example.demo.service;

import com.example.demo.dto.AddressDto;

import java.security.Principal;

public interface AddressService {



    AddressDto findById(Long id);
    AddressDto findByLogin(Principal principal);



}
