package com.example.demo.entity.Address.dto;

import com.example.demo.entity.Address.Address;

public class AddressMapper {


    public static Address mapAddressDtoToAddress(Address address, AddressDto addressDto) {

        address.setCity(addressDto.getCity());
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setZippCode(addressDto.getZippCode());

        return address;
    }

    public static AddressDto mapAddressToAddressDto(Address address) {

        AddressDto addressDto = new AddressDto();

        if (address != null) {
            addressDto.setCity(address.getCity());
            addressDto.setHouseNumber(address.getHouseNumber());
            addressDto.setZippCode(address.getZippCode());
        }
        return addressDto;

    }


}
