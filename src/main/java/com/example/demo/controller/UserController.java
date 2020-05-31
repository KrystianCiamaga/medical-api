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
@RequestMapping("/api/users")
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



    @GetMapping("/token")
    public void validToken(@RequestParam String value) {
        userService.checkToken(value);
    }

}
