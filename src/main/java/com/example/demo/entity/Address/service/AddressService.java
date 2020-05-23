package com.example.demo.entity.Address.service;

import com.example.demo.entity.Address.Address;
import com.example.demo.entity.Address.dto.AddressDto;

import java.security.Principal;

public interface AddressService {



    AddressDto findById(Long id);
    AddressDto findByLogin(Principal principal);



}
