package com.example.demo.controller;

import com.example.demo.entity.User.User;
import com.example.demo.entity.User.service.UserService;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    @Autowired
    PatientRepository patientRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void saveUser(@RequestBody User user) throws Exception {

        userService.saveUser(user);

    }





}
