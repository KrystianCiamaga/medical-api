package com.example.demo.controller;

import com.example.demo.entity.Token;
import com.example.demo.entity.User.User;
import com.example.demo.entity.User.service.UserService;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserService userService;
    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Autowired
    PatientRepository patientRepository;

    public UserController(UserService userService, TokenRepository tokenRepository, UserRepository userRepository, PatientRepository patientRepository) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @PostMapping("/register")
    public void saveUser(@RequestBody User user) throws Exception {

        userService.saveUser(user);

    }

    @GetMapping("/token")
    public void checkToken(@RequestParam String value){
        Token token=tokenRepository.findByValue(value);
       User user = token.getUser();
        user.setActive(true);
        userRepository.save(user);

    }



}
