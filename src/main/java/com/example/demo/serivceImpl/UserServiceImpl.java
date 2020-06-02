package com.example.demo.serivceImpl;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.UserCreateDto;
import com.example.demo.dto.UserReadDto;
import com.example.demo.email.MailService;
import com.example.demo.entity.Token;
import com.example.demo.entity.User;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final TokenRepository tokenRepository;

    private final MailService mailService;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, DoctorRepository doctorRepository,
                           TokenRepository tokenRepository, MailService mailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void saveUser(UserCreateDto user) throws Exception {

        if(user.getRole() == null || user.getRole().isEmpty()) {
            throw new Exception("User must have at least  a one  role set!");
        }

        if(userRepository.findByLogin(user.getLogin()) !=null){
            throw new AlreadyExistsException("User with this login already exists");
        }

        User user1 = UserMapper.mapUserCreateDtoToUser(user);

        userRepository.save(user1);


        }



  @Override
    public void checkToken(String value){
        Token token=tokenRepository.findByValue(value);
        User user = token.getUser();
        user.setActive(true);
        userRepository.save(user);

    }


}
