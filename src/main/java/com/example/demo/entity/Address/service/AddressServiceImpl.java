package com.example.demo.entity.Address.service;

import com.example.demo.entity.Address.dto.AddressDto;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AddressServiceImpl implements AddressService {


    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AddressDto findById(Long id) {



        return null;
    }

    @Override
    public AddressDto findByLogin(Principal principal) {




        return null;
    }
}
