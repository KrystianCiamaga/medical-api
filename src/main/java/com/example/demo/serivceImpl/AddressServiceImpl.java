package com.example.demo.serivceImpl;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Address;
import com.example.demo.entity.Patient;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.AddressMapper;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AddressService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

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

        Optional<Address> byId = Optional.ofNullable(addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find address with id: " + id)));


        return AddressMapper.mapAddressToAddressDto(byId.get());
    }

    @Override
    public void deleteAddress(Long id) {

        addressRepository.deleteAddressById(id);

    }


    @Override
    public AddressDto findLoggedUserAddress(Principal principal) {

        User user = userRepository.findByLogin(principal.getName());
        return AddressMapper.mapAddressToAddressDto(user.getAddress());
    }

    @Override
    public void updateLoggedUserAddress(AddressDto addressDto, Principal principal) {

        User user = userRepository.findByLogin(principal.getName());

        Address address = user.getAddress();

        Address newAddress = AddressMapper.mapAddressDtoToAddress(address, addressDto);

        user.setAddress(newAddress);
        addressRepository.saveAndFlush(newAddress);
        userRepository.saveAndFlush(user);


    }

}
