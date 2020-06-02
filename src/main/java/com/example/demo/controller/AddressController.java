package com.example.demo.controller;


import com.example.demo.dto.AddressDto;
import com.example.demo.service.AddressService;
import org.hibernate.event.spi.PreInsertEvent;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {

        this.addressService = addressService;
    }


    @GetMapping("/showAddress")
    public AddressDto getLoggedUserAddress(Principal principal){
        return addressService.findLoggedUserAddress(principal);
    }


    @PutMapping("/updateAddress")
    public void updateLoggedUserAddress(@RequestBody AddressDto addressDto, Principal principal){

        addressService.updateLoggedUserAddress(addressDto,principal);

    }





}
