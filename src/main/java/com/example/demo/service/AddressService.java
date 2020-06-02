package com.example.demo.service;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Address;

import java.security.Principal;

public interface AddressService {



    AddressDto findById(Long id);
    void deleteAddress(Long id);
    AddressDto findLoggedUserAddress(Principal principal);
    void updateLoggedUserAddress(AddressDto addressDto,Principal principal);



}
