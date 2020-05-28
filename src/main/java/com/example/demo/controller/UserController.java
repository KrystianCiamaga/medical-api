package com.example.demo.controller;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserReadDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody UserCreateDto user) throws Exception {
        userService.saveUser(user);
    }

    @GetMapping("/all")
    public List<UserReadDto> getalll() {

        return userRepository.findAll().stream().
                map(UserMapper::mapUserToUserReadDto)
                .collect(Collectors.toList());
    }


    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/address")
    public AddressDto getLoggedUserAddres(Principal principal) {
        return userService.findLoggedUserAddress(principal);
    }

    @GetMapping("/token")
    public void validToken(@RequestParam String value) {
        userService.checkToken(value);
    }

}
