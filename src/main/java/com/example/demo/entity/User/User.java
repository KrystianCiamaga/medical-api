package com.example.demo.entity.User;

import com.example.demo.enums.Gender;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public  class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String password;

    private String email;

    private String role;

    private boolean isActive;



}
