package com.example.demo.entity.Address.service;

import com.example.demo.entity.Address.dto.AddressDto;
import com.example.demo.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {


    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public AddressDto findById(Long id) {



        return null;
    }
}
